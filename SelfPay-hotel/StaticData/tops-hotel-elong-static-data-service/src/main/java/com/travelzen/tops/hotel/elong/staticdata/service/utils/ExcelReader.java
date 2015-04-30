package com.travelzen.tops.hotel.elong.staticdata.service.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelReader {
	
	private static Logger LOG = LoggerFactory.getLogger(ExcelReader.class);
	
	public static final String EXCEL_XLS = "xls";
	public static final String EXCEL_XLSX = "xlsx";

	/**
	 * excel读取工具，获得工作表
	 * @author muyuansun
	 * @date 2014-10-14 下午4:05:59
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static final Workbook createWb(String filePath) throws IOException {
		if (StringUtils.isBlank(filePath)) {
			throw new IllegalArgumentException("请输入excel文件路径");
		}
		if (filePath.trim().toLowerCase().endsWith(EXCEL_XLS)) {
			return new HSSFWorkbook(new FileInputStream(filePath));
		} else if (filePath.trim().toLowerCase().endsWith(EXCEL_XLSX)) {
			return new XSSFWorkbook(new FileInputStream(filePath));
		} else {
			throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式!!!");
		}
	}
	
	public static final Workbook createWb(InputStream inputStream,String excelFileType) throws IOException {
		if (inputStream == null) {
			throw new IllegalArgumentException("请提供excel文件内容输入流");
		}
		if (excelFileType == null || excelFileType.length() <= 0) {
			throw new IllegalArgumentException("请提供excel文件类型");
		}
		if (excelFileType.trim().toLowerCase().endsWith(EXCEL_XLS)) {
			return new HSSFWorkbook(inputStream);
		} else if (excelFileType.trim().toLowerCase().endsWith(EXCEL_XLSX)) {
			return new XSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式!!!");
		}
	}

	public static final Sheet getSheet(Workbook wb, String sheetName) {
		return wb.getSheet(sheetName);
	}

	public static final Sheet getSheet(Workbook wb, int index) {
		return wb.getSheetAt(index);
	}
	/**
	 * 提取sheet中的行信息
	 * @author muyuansun
	 * @date 2014-10-14 下午4:23:23
	 * @param sheet
	 * @return
	 */
	public static final List<Object[]> listFromSheet(Sheet sheet) {
		List<Object[]> result = null; 
		if(sheet == null){
			return result; 
		}
		int rowTotal = sheet.getPhysicalNumberOfRows();
		LOG.info("[Sheet = {}][共有 {} 行记录！]",sheet.getSheetName(), rowTotal);
		result = new ArrayList<Object[]>();
		for (int r = sheet.getFirstRowNum(); r <= sheet.getLastRowNum(); r++) {
			Row row = sheet.getRow(r);
			if (row == null){
				continue;
			}
			// 不能用row.getPhysicalNumberOfCells()，可能会有空cell导致索引溢出
			// 使用row.getLastCellNum()至少可以保证索引不溢出，但会有很多Null值，如果使用集合的话，就不说了
			Object[] cells = new Object[row.getLastCellNum()];
			for (int c = row.getFirstCellNum(); c <= row.getLastCellNum(); c++) {
				Cell cell = row.getCell(c);
				if (cell == null){
					continue;
				}
				cells[c] = getValueFromCell(cell);
			}
			result.add(cells);
		}
		return result;
	}
	/**
	 * 提取单元格的内容
	 * @author muyuansun
	 * @date 2014-10-14 下午4:18:46
	 * @param cell
	 * @return
	 */
    public static final String getValueFromCell(Cell cell) {
        if(cell == null) {
            return null ;
        }
        String value = null ;
        switch(cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC :   // 数字
                value = String.valueOf(cell.getNumericCellValue()) ;
                break ;
            case Cell.CELL_TYPE_STRING:     // 字符串
                value = cell.getStringCellValue() ;
                break ;
            case Cell.CELL_TYPE_BOOLEAN:    // Boolean
                value = String.valueOf(cell.getBooleanCellValue()) ;
                break ;
            case Cell.CELL_TYPE_ERROR:      // Error，返回错误码
                value = String.valueOf(cell.getErrorCellValue()) ;
                break ;
            default:value = StringUtils.EMPTY ;break ;
        }
        return value;
    } 

}
