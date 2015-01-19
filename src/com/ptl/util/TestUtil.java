package com.ptl.util;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.ptl.testcases.TestBase;

public class TestUtil {

	public static boolean isTestCaseRunmodeYes(String testCaseName,	ReadXLS xls) {

		for (int rowNum = 2; rowNum <= xls.getRowCount("Test Cases"); rowNum++) {
			if (xls.getCellData("Test Cases", "TCID", rowNum).equalsIgnoreCase(testCaseName)) {
				if (xls.getCellData("Test Cases", "Runmode", rowNum).equalsIgnoreCase("Yes")){
					// String testDataSheet= xls.getCellData("Test Case", "TestCaseID", rowNum);
					System.out.println("rowNum: "+ rowNum);
					System.out.println("Runmode " + xls.getCellData(testCaseName, "Runmode", rowNum));
					return true;
				}
				else{
					System.out.println("rowNum: "+ rowNum);
					return false;
				}
			}
		}
		return false;
	}
	
	
	public static Object[][] getTestData(String testCaseName, ReadXLS xls){
		
		//Test data starting row number
		//int testDataStartRowNum = 2;
		//number of data rows
		int totalDataRows = xls.getRowCount(testCaseName);
		//number of columns
		int totalCols = xls.getColumnCount(testCaseName);
		
		Object[][] data = new Object[totalDataRows - 1][1];
		int index=0;
		
		Hashtable<String, String> table = null;
		for(int dataRowNum=2; dataRowNum<=totalDataRows; dataRowNum++){
			table = new Hashtable<String, String>();
			
			for(int dataColNum=0; dataColNum<totalCols; dataColNum++){
				table.put(xls.getCellData(testCaseName, dataColNum, 1), 
						xls.getCellData(testCaseName, dataColNum, dataRowNum));
				System.out.print(xls.getCellData(testCaseName, dataColNum, dataRowNum)+ " -- ");
			}
			data[index][0] = table;
			index++;
			System.out.println();			
		}
		
		System.out.println("done");
		System.out.println("Number of data rows: "+ (totalDataRows-1));
		
		return data;
		
	}
	
	public static void takeScreenShot(String fileName) {
		File srcFile = ((TakesScreenshot)(TestBase.driver)).getScreenshotAs(OutputType.FILE);
	    try {
	    	/*Date date = new Date(System.currentTimeMillis());*/				
	        String dateString = String.valueOf(System.currentTimeMillis());
	    	FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+fileName+dateString+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
