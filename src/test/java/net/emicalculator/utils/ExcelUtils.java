package net.emicalculator.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	FileInputStream fileInputStream;
	FileOutputStream fileOutputStream;

	public void writeExcelData(String[][] rowData, String filename) {

		try {

			String filePath = (".//ExcelData/" + filename + ".xlsx");

			fileInputStream = new FileInputStream(filePath);

			XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet sheet = workbook.createSheet("YearlyData");

			int lastRow = sheet.getLastRowNum();

			for (short i = 1; i <= rowData.length; i++) {
				XSSFRow row = sheet.createRow(lastRow + i);

				for (byte j = 0; j < rowData[i - 1].length; j++) {
					row.createCell(j).setCellValue(rowData[i - 1][j]);
				}
			}

			fileInputStream.close();

			fileOutputStream = new FileOutputStream(filePath);

			workbook.write(fileOutputStream);

			fileOutputStream.close();

			workbook.close();

		} catch (Exception e) {
			System.out.println("Failed to write data in excel " + e.getMessage());
		}

	}

	public String[][] readData(String filename,String sheetName) {

		try {
			

			String filePath = (".//ExcelData/" + filename + ".xlsx");

			fileInputStream = new FileInputStream(filePath);

			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			XSSFRow headerRow = sheet.getRow(0);

			int lastRow = sheet.getLastRowNum();
			int lastCol = headerRow.getLastCellNum();
			
			String [][] data = new String[lastRow][lastCol];

			for (short i = 0; i < lastRow; i++) {
				XSSFRow dataRow = sheet.getRow(i+1);
				
				for (byte j = 0; j < lastCol; j++) {
					data[i][j] = dataRow.getCell(j).toString();
				}
			}

			fileInputStream.close();

			workbook.close();
			
			return data;

		} catch (Exception e) {
			System.out.println("Failed to write data in excel " + e.getMessage());
			return null;
		}

	}
}
