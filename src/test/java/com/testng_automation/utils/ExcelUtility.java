package com.testng_automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.testng_automation.context.Constance;

public class ExcelUtility {
	
	public static Hashtable<String, HashMap<String, String>> getData(String testCaseId) {
		
		Hashtable<String, HashMap<String, String>> data = getTestCaseData().get(testCaseId);
		
		if(data.size() > 1) {
			System.out.println("mutiple test data involved please use data provider");
			return null;
		}
		
		return data;
	}
	
	public static ArrayList<HashMap<String, String>> getDataList(String testCaseId){
		Hashtable<String, HashMap<String, String>> data = getTestCaseData().get(testCaseId);
		if(data.size() == 2) {
			System.out.println("Only one data, data provider required");
		}
		
		ArrayList<HashMap<String, String>> dataList = new ArrayList<>(data.values());
		
		return dataList;
	}
	
	public static Object[][] getDataProvider(String testCaseNumber){
		
		ArrayList<HashMap<String, String>> dataList = getDataList(testCaseNumber);
		
		Object[][] testData = new Object[dataList.size()][1];
		for (int i = 0; i < dataList.size(); i++) {
			testData[i][0] = dataList.get(i);
		}
		return testData;
	}
	
	public static Hashtable<String, Hashtable<String, HashMap<String, String>>> getTestCaseData(){
		try {
			  FileInputStream  inp = new FileInputStream(new File(Constance.WORKING_DIR + "/target/test-data/testdata.xlsx"));
			 OPCPackage pkg = OPCPackage.open(inp);
			 XSSFWorkbook wb = new XSSFWorkbook(pkg);

			Sheet sheet = wb.getSheetAt(0);
			
			Hashtable<String, Hashtable<String, HashMap<String, String>>> testData = new Hashtable<>();

			int columns = sheet.getRow(0).getPhysicalNumberOfCells();
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			    Row row = sheet.getRow(i);
			    if (row != null) {
			        String tcId = row.getCell(0) != null ? row.getCell(0).toString() : "";
			        String tdId = row.getCell(1) != null ? row.getCell(1).toString() : "";

			        // Create inner Hashtable for the test case if not already present
			        if (!testData.containsKey(tcId)) {
			            testData.put(tcId, new Hashtable<>());
			        }
			        Hashtable<String, HashMap<String, String>> tcData = testData.get(tcId);

			        // Create inner HashMap for the test data if not already present
			        if (!tcData.containsKey(tdId)) {
			            tcData.put(tdId, new HashMap<>());
			        }
			        HashMap<String, String> tdData = tcData.get(tdId);

			        // Populate the inner HashMap with data dynamically based on column details
			        for (int j = 2; j < columns; j++) {
			            Cell headerCell = sheet.getRow(0).getCell(j);
			            Cell dataCell = row.getCell(j);
			            if (headerCell != null) {
			                String headerValue = headerCell.toString();
			                String dataValue = dataCell != null ? dataCell.toString() : "";
			                if (headerValue.equals("errormessage") && dataValue == null) {
			                    // If the header is "errormessage" and the data is null, set it to an empty string
			                    dataValue = "";
			                }
			                tdData.put(headerValue, dataValue);
			            }
			        }
			    }
			}
            
            System.out.println(testData);


			
			wb.close();
			inp.close();
			
			return testData;
			
			
		} catch (IOException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
