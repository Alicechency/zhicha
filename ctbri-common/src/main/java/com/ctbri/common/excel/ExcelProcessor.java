package com.ctbri.common.excel;

import java.io.File;
import java.io.IOException;

/**
 * Excel处理器，兼容2003/2007
 * 
 * @author Hogan
 *
 */
public abstract class ExcelProcessor implements ExcelRowProcessor {

	private ExcelRowProcessor processor;

	public ExcelProcessor(String filePath) throws Exception {
		if (filePath == null || "".equals(filePath)) {
			throw new Exception("构造Excel导入器失败，未指定文件全名。");
		}
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("构造Excel导入器失败，指定的文件不存在：" + filePath);
		}
		if (filePath.endsWith("xls")) {
			processor = new DefaultExcel2003RowProcessor(filePath);
		} else {
			processor = new DefaultExcel2007RowProcessor(filePath);
		}
	}

	public abstract void processRow(XRow row);

	public abstract void processRowResult(boolean isOK);
	
	public void processByRow() throws Exception {
		processor.processByRow();
	}

	public void processByRow(int sheetIndex) throws Exception {
		processor.processByRow(sheetIndex);
	}

	public void stop() throws IOException {
		processor.stop();
	}

	/**
	 * 默认的2003处理器
	 * 
	 * @author Hogan
	 *
	 */
	private class DefaultExcel2003RowProcessor extends Excel2003RowProcessor {

		public DefaultExcel2003RowProcessor(String filename) throws Exception {
			super(filename);
		}

		@Override
		public void processRow(XRow row) {
			ExcelProcessor.this.processRow(row);
		}

		@Override
		public void processRowResult(boolean isOK) {
			ExcelProcessor.this.processRowResult(isOK);
		}
	}

	/**
	 * 默认的2007处理器
	 * 
	 * @author Hogan
	 *
	 */
	private class DefaultExcel2007RowProcessor extends Excel2007RowProcessor {

		public DefaultExcel2007RowProcessor(String filename) throws Exception {
			super(filename);
		}

		@Override
		public void processRow(XRow row) {
			ExcelProcessor.this.processRow(row);
		}

		@Override
		public void processRowResult(boolean isOK) {
			ExcelProcessor.this.processRowResult(isOK);

		}
	}
}
