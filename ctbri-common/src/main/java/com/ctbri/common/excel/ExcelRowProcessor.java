package com.ctbri.common.excel;

import java.io.IOException;

import com.ctbri.common.utils.StringUtils;

/**
 * Excel行级处理接口
 * 
 * @author Hogan
 *
 */
public interface ExcelRowProcessor {

	static final String CELL = "c";

	/**
	 * 读取所有的excel
	 * 
	 * @throws Exception
	 */
	public void processByRow() throws Exception;

	/**
	 * 读取指定sheet编号的excel
	 * 
	 * @param sheetIndex
	 * @throws Exception
	 */
	public void processByRow(int sheetIndex) throws Exception;

	/**
	 * 对每一行数据的处理
	 * 
	 * @param row
	 */
	public void processRow(XRow row);

	/**
	 * 停止读取excel
	 * 
	 * @throws IOException
	 */
	public void stop() throws IOException;

	/**
	 * 是否为空行
	 * 
	 * @param row
	 * @return
	 */
	public default boolean isBlankRow(XRow row) {
		boolean b = true;
		for (int i = 0; i < row.getCellsSize(); i++) {
			XCell cell = row.getCell(i);
			if (!StringUtils.isNullOrBlank(cell.getValue())) {
				b = false;
			}
		}
		return b;
	}

}
