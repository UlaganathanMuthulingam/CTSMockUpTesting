package com.cts.springboot.com.cts.mockupProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class ReadExcelData {

	
	private Resources resources;
	private UpdateMessage updateMessage;
	private FileInputStream fileInputStream;
	private FileOutputStream fileoutputStream;
	private File file;
	private XSSFWorkbook workBook;
	private XSSFSheet workSheet;


	
	public FileOutputStream getFileoutputStream() {
		return fileoutputStream;
	}

	public void setFileoutputStream(FileOutputStream fileoutputStream) {
		this.fileoutputStream = fileoutputStream;
	}

	public XSSFWorkbook getWorkBook() {
		return workBook;
	}

	public void setWorkBook(XSSFWorkbook workBook) {
		this.workBook = workBook;
	}

	public XSSFSheet getWorkSheet() {
		return workSheet;
	}

	public void setWorkSheet(XSSFSheet workSheet) {
		this.workSheet = workSheet;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file2) {
		this.file = file2;
	}

	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public ReadExcelData() {

	}

	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	public ReadExcelData(Resources resource) {
		this.resources = resource;
	}

	public UpdateMessage getUpdateMessage() {
		return updateMessage;
	}

	public void setUpdateMessage(UpdateMessage updateMessage) {
		this.updateMessage = updateMessage;
	}

	
	
	public void workbook() {
		try {
			setFile(new File("src/main/resources/MockedExcel/MockedTestData.xlsx"));
			
			FileInputStream input = new FileInputStream(getFile());
			setFileInputStream(input);
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			setWorkBook(workbook);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			setWorkSheet(sheet);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public ResponseEntity<UpdateMessage> updateDetails(UpdateMessage msg, String employeeName) {

		workbook();

		int recordpresentFlag = recordPresent(getWorkBook(), getWorkSheet(), getResources().getId());
		if (recordpresentFlag != 99999 && getResources().getEmployeename().equalsIgnoreCase(employeeName) == true)

		{
			updateValuesInExcel();
			msg.setResponseMessage("Given employee details updated successfully");
			return new ResponseEntity<UpdateMessage>(msg, HttpStatus.OK);
			// return 200;

		}

		else if (recordpresentFlag != 99999
				&& getResources().getEmployeename().equalsIgnoreCase(employeeName) == false) {
			msg.setResponseMessage("Invalid employee supplied");
			return new ResponseEntity<UpdateMessage>(msg, HttpStatus.BAD_REQUEST);
		}

		else {
			msg.setResponseMessage("employee not found");
			return new ResponseEntity<UpdateMessage>(msg, HttpStatus.NOT_FOUND);

		}

	}

	public void updateValuesInExcel() {
			XSSFWorkbook workbook = getWorkBook();
			XSSFSheet sheet = getWorkSheet();
			int rowNum = recordPresent(workbook, sheet, getResources().getId());
				updateExcelValueForInteger(sheet, rowNum, 0, getResources().getId());
				updateExcelValueForString(sheet, rowNum, 1, getResources().getEmployeename());
				updateExcelValueForString(sheet, rowNum, 2, getResources().getFirstName());
				updateExcelValueForString(sheet, rowNum, 3, getResources().getLastName());
				updateExcelValueForString(sheet, rowNum, 4, getResources().getEmail());
				updateExcelValueForString(sheet, rowNum, 5, getResources().getPassword());
				updateExcelValueForString(sheet, rowNum, 6, getResources().getPhone());
				updateExcelValueForInteger(sheet, rowNum, 7, getResources().getEmployeeStatus());
				writeValuesInOutputStream(getFile(), workbook);			
	} 
		



	public int recordPresent(XSSFWorkbook workBook, XSSFSheet sheetName, int idName) {
		for (int row = 1; row <= sheetName.getLastRowNum(); row++) {
			if (sheetName.getRow(row).getCell(0).getNumericCellValue() == idName) {
				return row;
			}
		}
		return 99999;

	}
	
	
	public int employeeNamePresent(XSSFWorkbook workBook, XSSFSheet sheetName, String employeeName) 
	{
		for (int row = 1; row <= sheetName.getLastRowNum(); row++) {
			String employeeNameFromExcel = sheetName.getRow(row).getCell(1).toString();
			if (employeeNameFromExcel.equals(employeeName)) {
				return row;
			}	
			
		}
		return 99999;

	}

	public int employeeNameAndPasswordrecordPresent(XSSFWorkbook workBook, XSSFSheet sheetName, String employeeName,
			String empPassword) {
		for (int row = 1; row <= sheetName.getLastRowNum(); row++) {

			String employeeNameFromExcel = sheetName.getRow(row).getCell(1).toString();
			String passwordFromExcel = sheetName.getRow(row).getCell(5).toString();
			if (employeeNameFromExcel.equals(employeeName) && passwordFromExcel.equals(empPassword)) {
				return row;
			}
		}
		return 99999;

	}

	public ResponseEntity<UpdateMessage> loginAPICall(UpdateMessage msg, String employeeName,String Password) 
	{
		workbook();
		XSSFWorkbook workbook = getWorkBook();
		XSSFSheet sheet = getWorkSheet();
		int rowNum = employeeNameAndPasswordrecordPresent(workbook, sheet, employeeName, Password);
		if(rowNum!=99999)
		{
			
			HttpHeaders headers = new HttpHeaders();
		    headers.add("X-Rate-Limit", "1");
		    headers.add("X-Expires-After", "Expired after an hour");
			
		    msg.setResponseMessage("successful operation");
		    UpdateMessage withOnlyResponseMessage=new UpdateMessage(msg.getResponseMessage());
		    return new ResponseEntity<UpdateMessage>(withOnlyResponseMessage, HttpStatus.OK);

		}
		
		else
		{
			msg.setResponseMessage("Invalid employeename/password supplied");
			return new ResponseEntity<UpdateMessage>(msg, HttpStatus.BAD_REQUEST);
		}
		
		
		
	}

	
	
	
	public ResponseEntity<?> getEmployeeAPICall(UpdateMessage msg, String employeeName) 
	{
		workbook();
		XSSFWorkbook workbook = getWorkBook();
		Resources resourceFromTable=new Resources();
		XSSFSheet sheet = getWorkSheet();
		int rowNum = employeeNamePresent(workbook, sheet, employeeName);
		if(rowNum!=99999)
		{
			resourceFromTable.setId(convertdoubleToint(sheet.getRow(rowNum).getCell(0).getNumericCellValue()));
			resourceFromTable.setEmployeename(sheet.getRow(rowNum).getCell(1).toString());
			resourceFromTable.setFirstName(sheet.getRow(rowNum).getCell(2).toString());
			resourceFromTable.setLastName(sheet.getRow(rowNum).getCell(3).toString());
			resourceFromTable.setEmail(sheet.getRow(rowNum).getCell(4).toString());
			resourceFromTable.setPassword(sheet.getRow(rowNum).getCell(5).toString());
			resourceFromTable.setPhone(sheet.getRow(rowNum).getCell(6).toString());
			resourceFromTable.setEmployeeStatus(convertdoubleToint(sheet.getRow(rowNum).getCell(7).getNumericCellValue()));
			return new ResponseEntity<Resources>(resourceFromTable, HttpStatus.OK);

		}
		
		else
		{
			msg.setResponseMessage("Invalid employeename/password supplied");
			return new ResponseEntity<UpdateMessage>(msg, HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	
	
	public  ResponseEntity<UpdateMessage> deleteRecord(UpdateMessage msg,String empName)
	{
		workbook();
		FileInputStream input = getFileInputStream();
		XSSFWorkbook workbook = getWorkBook();
		XSSFSheet sheet = getWorkSheet();
		int rowNum = employeeNamePresent(workbook, sheet, empName);
		if(rowNum!=99999)
		{
			XSSFRow row=sheet.getRow(rowNum);
			deleteRow(sheet, row); 
			sheet.shiftRows(rowNum+1,sheet.getLastRowNum(), -1);
			//input.close();
			writeValuesInOutputStream(getFile(), workbook);	
			msg.setResponseMessage("Record deleted Successfully");
			return new ResponseEntity<UpdateMessage>(msg, HttpStatus.OK);
			
		}
		else
		{
			msg.setResponseMessage("employee not found");
			return new ResponseEntity<UpdateMessage>(msg, HttpStatus.NOT_FOUND);
		}
		
	}

	public ResponseEntity<UpdateMessage> createUser(UpdateMessage msg) 
	{
		workbook();
		XSSFWorkbook workbook = getWorkBook();
		XSSFSheet sheet = getWorkSheet();
		int rowNum = recordPresent(workbook, sheet, getResources().getId());
		if(rowNum==99999)
		{
			int lastrow=sheet.getLastRowNum();
			
			updateExcelValueForInteger(sheet, lastrow, 0, getResources().getId());
			updateExcelValueForString(sheet, lastrow, 1, getResources().getEmployeename());
			updateExcelValueForString(sheet, lastrow, 2, getResources().getFirstName());
			updateExcelValueForString(sheet, lastrow, 3, getResources().getLastName());
			updateExcelValueForString(sheet, lastrow, 4, getResources().getEmail());
			updateExcelValueForString(sheet, lastrow, 5, getResources().getPassword());
			updateExcelValueForString(sheet, lastrow, 6, getResources().getPhone());
			updateExcelValueForInteger(sheet, lastrow, 7, getResources().getEmployeeStatus());
			writeValuesInOutputStream(getFile(), workbook);
			msg.setResponseMessage("successful operation");
			return new ResponseEntity<UpdateMessage>(msg, HttpStatus.OK);

		}
		else
		{
			msg.setResponseMessage("Record already exist");
			return new ResponseEntity<UpdateMessage>(msg, HttpStatus.CONFLICT);
		}
		

		
	}
	
	public void updateExcelValueForString(XSSFSheet sheetNamToupdate, int rowNumber, int columnNumber,
			String StringvalueToUpdate) {

		sheetNamToupdate.getRow(rowNumber).getCell(columnNumber).setCellValue(StringvalueToUpdate);
	}

	public void updateExcelValueForInteger(XSSFSheet sheetNamToupdate, int rowNumber, int columnNumber,
			int intvalueToUpdate) {

		sheetNamToupdate.getRow(rowNumber).getCell(columnNumber).setCellValue(intvalueToUpdate);
	}
	
	

	public void deleteRow(XSSFSheet sheetNamToupdate, XSSFRow xssfRow) {		
			sheetNamToupdate.removeRow(xssfRow);
			
	}

	public void writeValuesInOutputStream(File fileLocation,XSSFWorkbook workbook)
	{
		try {
			FileOutputStream outFile = new FileOutputStream(fileLocation);
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
	}
	
	public int convertdoubleToint(double num)
	{
		return (int) num;
	}
	
}
