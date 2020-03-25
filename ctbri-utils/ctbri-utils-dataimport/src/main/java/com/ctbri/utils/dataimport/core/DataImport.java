package com.ctbri.utils.dataimport.core;

import org.apache.log4j.Logger;

import com.ctbri.utils.dataimport.util.Consts;

/**
 * 数据导入
 * 
 * @author Hogan
 *
 */
public class DataImport {

	private Logger log = Logger.getLogger(getClass());
	private GlobalProperty globalProperty = GlobalProperty.newInstance();

	private Integer type;
	private long sumRows;
	private DataTemplate dataTemplate;
	private ExcelOpt excelOpt;
	private ESTemplate esTemplate = new ESTemplate();

	public DataImport(String dataFilePath, int type) {
		this.type = type;
		dataTemplate = new DataTemplate();
		try {
			excelOpt = new ExcelOpt(dataFilePath);
			setSumRows(Consts.INIT_SUM_ROWS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导入数据
	 */
	public void dimport() {
		try {
			log.info("开始导入数据");
			dataTemplate.excute(globalProperty.getSqlCreate(type));
			excelOpt.process(type);
			dataTemplate.batchInsert(globalProperty.getSqlInsert(type), excelOpt.getValues());
			if (globalProperty.isPushES()) {
				esTemplate.batchInsert(excelOpt.getValues());
			}
			log.info("已完成:" + excelOpt.getCount() + "条");
			log.info("导入数据完成!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getSumRows() {
		return sumRows;
	}

	public void setSumRows(long sumRows) {
		this.sumRows = sumRows;
	}

	public long getAlreadyHandleCount() {
		return excelOpt.getCount();
	}

}
