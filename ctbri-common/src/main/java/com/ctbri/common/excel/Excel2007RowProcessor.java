package com.ctbri.common.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Excel-2007行级处理器
 * 
 * @author Hogan
 *
 */
public abstract class Excel2007RowProcessor implements ExcelRowProcessor {

	int sheetIndex = -1;
	private int curRow = 0;
	private int curCol = 0;
	private String fileName;
	private MyHander hander;
	private OPCPackage pkg;
	private List<String> rowlist = new ArrayList<String>();

	/**
	 * 构造Excel-2007行级解析器
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public Excel2007RowProcessor(String filePath) throws Exception {
		if (filePath.endsWith(".xls")) {
			throw new Exception("Excel板式与解析器不匹配，解析器仅支持Excel-2007及以上版本。");
		}
		this.fileName = filePath;
		this.hander = new MyHander();
	}

	public abstract void processRow(XRow row);

	public abstract void processRowResult(boolean isOK);

	/**
	 * 辅助实现方法，XML解析
	 * 
	 * @param sst
	 * @return
	 * @throws SAXException
	 */
	private XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
		XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		hander.setSst(sst);
		parser.setContentHandler(hander);
		return parser;
	}

	/**
	 * 处理所有sheet
	 */
	public void processByRow() throws Exception {
		curRow = 0;
		pkg = OPCPackage.open(fileName);
		MyXXSFReader r = new MyXXSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);
		Iterator<InputStream> sheets = r.getSheetsData();
		while (sheets.hasNext()) {
			curRow = 0;
			sheetIndex++;
			InputStream sheet = sheets.next();
			InputSource sheetSource = new InputSource(sheet);
			parser.parse(sheetSource);
			sheet.close();
		}

	}

	/**
	 * 处理指定索引的sheet
	 */
	public void processByRow(int optSheetIndex) throws Exception {
		curRow = 0;
		pkg = OPCPackage.open(fileName);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);
		InputStream sheet = r.getSheet("rId" + optSheetIndex);
		sheetIndex++;
		InputSource sheetSource = new InputSource(sheet);
		parser.parse(sheetSource);
		sheet.close();
	}

	public void stop() throws IOException {
		if (pkg != null) {
			pkg.close();
		}
	}

	/**
	 * 辅助实现类，解析excel元素的句柄
	 * 
	 * @author zhangchaofeng
	 * @version 1.0
	 * @date Sep 28, 2011
	 */
	private class MyHander extends DefaultHandler {
		private SharedStringsTable sst;
		private String lastContents;
		private boolean nextIsString;
		private boolean closeV = false;;

		public void setSst(SharedStringsTable sst) {
			this.sst = sst;
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			lastContents += new String(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String name) throws SAXException {
			if (nextIsString) {
				try {
					int idx = Integer.parseInt(lastContents);
					lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
				} catch (Exception e) {
					// nope
				}
			}
			if (name.equals("v")) {
				String value = lastContents.trim();
				value = value.equals("") ? " " : value;
				rowlist.add(curCol, value);
				curCol++;
				closeV = true;
			} else {
				if (name.equals("c")) {
					if (!closeV) {
						rowlist.add(curCol, " ");
						curCol++;
					}
				}
				if (name.equals("row")) {
					XRow row = new XRow();
					for (int i = 0; i < rowlist.size(); i++) {
						XCell cell = new XCell();
						cell.setColumnIndex(i + 'A');
						cell.setRowIndex(curRow + 1);
						cell.setValue((String) rowlist.get(i));
						row.setRowIndex(curRow + 1);
						row.addCell(cell);
					}
					if (!isBlankRow(row)) {
						processRow(row);
					} else {
						processRowResult(true);
					}
					rowlist.clear();
					curRow++;
					curCol = 0;
				}
			}
		}

		@Override
		public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
			if (CELL.equals(name)) {
				String cellType = attributes.getValue("t");
				if (cellType != null && cellType.equals("s")) {
					nextIsString = true;
				} else {
					nextIsString = false;
				}
				closeV = false;
			}
			lastContents = "";
		}

	}
}