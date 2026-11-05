import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public ArrayList<String> getData(String testCaseType) throws IOException {
		String projectPath = System.getProperty("user.dir");
		FileInputStream fileStream = new FileInputStream(projectPath+"/ExcelDriven/inputFolder/demoData.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		int totalSheets = workbook.getNumberOfSheets();
		System.out.println("Total Sheets: " + totalSheets);
		XSSFSheet sheet = null;

		ArrayList a = new ArrayList<String>();

		for (int i = 0; i < totalSheets; i++) {
			String sheetName = workbook.getSheetName(i);
			System.out.println(sheetName);
		}

		for (int i = 0; i < totalSheets; i++) {

			System.out.println("I am at: --->" + workbook.getSheetName(i));
			if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
				sheet = workbook.getSheetAt(i);
				// sheet = workbook.getSheet("Sheet1");

				Iterator<Row> rows = sheet.iterator();
				Row row = rows.next(); // this will fetch the first row

				Iterator<Cell> cell = row.cellIterator();
				// System.out.println(cell.next().getStringCellValue());
				while (cell.hasNext()) {
					Cell value = cell.next(); // this will fetch the first cell in the specified row
					System.out.println("*********" + value.getStringCellValue());
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						System.out.println("TestCases found in column: " + value.getColumnIndex());
						int testCaseColumn = value.getColumnIndex();
						while (rows.hasNext()) {
							row = rows.next();
							if (row.getCell(testCaseColumn).getStringCellValue().equalsIgnoreCase(testCaseType)) {
								Iterator<Cell> c = row.cellIterator();
								while (c.hasNext()) {
									Cell cellValue = c.next();
									if (cellValue.getCellType()==CellType.STRING) {
										System.out.println(cellValue.getStringCellValue());
										a.add(cellValue.getStringCellValue());
									}
									 else {
										System.out.println(cellValue.getNumericCellValue());
										//a.add(cellValue.getNumericCellValue());
										a.add(NumberToTextConverter.toText(cellValue.getNumericCellValue()));
									}
								}
							}
						}
					}

				}
				break;
			}
			 //break;
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		dataDriven d = new dataDriven();
		ArrayList<String> data = d.getData("Purchase");
		System.out.println("***************************  "+"Here is the ArrayList data" + "  ***************************");
		  System.out.println(data.get(0)); 
		  System.out.println(data.get(1));
		  System.out.println(data.get(2));
		  System.out.println(data.get(3));
		 

	}
}
