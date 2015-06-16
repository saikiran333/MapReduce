package com.cloudwick.maven.readtext;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.avro.example.Employee;

public class ReadTextWriteAVRO {

	public static void main(String[] args) {
		readTextWriteAVRO();
		
		readAVROWriteText();
	}
	
	public static void setEmployeeDetails(Employee emp, String line) {
		String [] entries = line.split(",");
		emp.setId(Integer.parseInt(entries[0]));
		emp.setName(entries[1]);
		emp.setDesignation(entries[2]);
		emp.setMgrid(Integer.parseInt(entries[3]));
		emp.setHiredate(entries[4]);
		emp.setSalary(Double.parseDouble(entries[5]));
		emp.setCommission(Double.parseDouble(entries[6]));
		emp.setDeptid(Integer.parseInt(entries[7]));
	}
	
	public static void readTextWriteAVRO() {
		try {
			String line = null;
			Employee empObj = new Employee();
			BufferedReader bufferReader = new BufferedReader(new FileReader("Employee.txt"));
			
			DatumWriter<Employee> userDatumWriter = new SpecificDatumWriter<Employee>(Employee.class);
			DataFileWriter<Employee> dataFileWriter = new DataFileWriter<Employee>(userDatumWriter);
			dataFileWriter.create(empObj.getSchema(), new File("ReadTextWriteAVRO.avro"));
			
			while ((line = bufferReader.readLine()) != null)   {
				setEmployeeDetails(empObj, line);
				dataFileWriter.append(empObj);
	          }
			dataFileWriter.close();
			bufferReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readAVROWriteText() {
		DatumReader<Employee> userDatumReader = new SpecificDatumReader<Employee>(Employee.class);
		DataFileReader<Employee> dataFileReader;
		try {
			dataFileReader = new DataFileReader<Employee>(new File("ReadTextWriteAVRO.avro"), userDatumReader);
			Employee emp = null;
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ReadTextWriteAVRO.txt"));
			
			while (dataFileReader.hasNext()) {
				emp = dataFileReader.next(emp);
				bufferedWriter.write(emp.toString());
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
