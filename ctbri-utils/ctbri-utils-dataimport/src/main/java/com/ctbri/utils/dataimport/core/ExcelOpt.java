package com.ctbri.utils.dataimport.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.ctbri.common.excel.ExcelProcessor;
import com.ctbri.common.excel.XRow;
import com.ctbri.utils.dataimport.util.Consts;
import com.ctbri.utils.dataimport.util.MapLocationUtil;

/**
 * Excel操作类
 * 
 * @author Hogan
 *
 */
public class ExcelOpt extends ExcelProcessor {

	private Logger log = Logger.getLogger(getClass());

	private int count = -1;
	private Integer type;
	private DataTemplate dataTemplate = new DataTemplate();
	private ESTemplate esTemplate = new ESTemplate();
	private List<List<String>> values = new ArrayList<List<String>>();
	private GlobalProperty globalProperty = GlobalProperty.newInstance();

	public ExcelOpt(String fileName) throws Exception {
		super(fileName);
	}

	public void processRow(XRow row) {
		if (count >= 0) {
			List<String> rowValue = new ArrayList<String>();
			for (int i = 0; i < row.getCellsSize(); i++) {
				String cellValue = row.getCell(i).getValue();
				rowValue.add(cellValue);
			}
			generateData(values, rowValue, type);
			if (count > 0 && count % Consts.DEFAULT_BATCH_SIZE == 0) {
				try {
					dataTemplate.batchInsert(globalProperty.getSqlInsert(type), values);
					if (globalProperty.isPushES()) {
						esTemplate.batchInsert(values);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				log.info("已完成:" + count + "条");
				values.clear();
			}
		} else {
			count++;
		}
	}

	@Override
	public void processRowResult(boolean isOK) {
		log.info(isOK);
	}

	/**
	 * 加工数据
	 * 
	 * @param values
	 * @param value
	 * @param type
	 */
	private void generateData(List<List<String>> values, List<String> value, Integer type) {
		List<String> result = new ArrayList<String>();
		String sql = null;
		if (type == Consts.SOURCE_TYPE_JQ) {
			sql = "select 序号 from dw_crimedata where 警情序号 = ?";
			if (!dataTemplate.isExist(sql, value.get(1))) {
				for (int i = 1; i < value.size(); i++) {
					if (i != value.size() - 2) {
						result.add(value.get(i));
					}
				}
				result.add(generateStrByRegex(value.get(2), globalProperty.getRegexIdentity()));// 事主
				String address = generateStrByRegex(value.get(3), globalProperty.getRegexLocation());
				if (address != null) {
					address = address.replaceAll(" +", "");
					result.add(address);
					Map<String, Double> map = MapLocationUtil.getLngAndLat(address);
					result.add(map.get("lng") + "");
					result.add(map.get("lat") + "");
				} else {
					result.add(null);
					result.add(null);
					result.add(null);
				}
				String jqCategory = value.get(value.size() - 2);
				if (jqCategory != null) {
					String[] categories = jqCategory.split("-");
					for (int i = 0; i < 5; i++) {
						if (i < categories.length) {
							result.add(categories[i]);
						} else {
							result.add(null);
						}
					}
				} else {
					result.add(null);
					result.add(null);
					result.add(null);
					result.add(null);
					result.add(null);
				}
				values.add(result);
				count++;
			}
		} else if (type == Consts.SOURCE_TYPE_CK) {
			sql = "select 序号 from dw_supervisorycontrol where 预警信息编号 = ?";
			if (!dataTemplate.isExist(sql, value.get(3))) {
				for (int i = 0; i < value.size() - 1; i++) {
					result.add(value.get(i + 1));
				}
				for (int i = result.size(); i < 46; i++) {
					result.add("");
				}
				String address = value.get(15);
				address = address.replaceAll(" +", "");
				Map<String, Double> map = MapLocationUtil.getLngAndLat(address);
				result.add(map.get("lng") + "");
				result.add(map.get("lat") + "");
				values.add(result);
				count++;
			}
		}
	}

	private String generateStrByRegex(Object input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((String) input);
		while (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	/**
	 * 操作
	 * 
	 * @param sheetIndex
	 * @param type
	 * @throws Exception
	 */
	public void process(Integer type) throws Exception {
		count = -1;
		this.type = type;
		processByRow();
	}

	public List<List<String>> getValues() {
		return values;
	}

	public void setValues(List<List<String>> values) {
		this.values = values;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
