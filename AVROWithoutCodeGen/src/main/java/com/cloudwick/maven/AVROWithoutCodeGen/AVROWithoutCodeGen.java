package com.cloudwick.maven.AVROWithoutCodeGen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

public class AVROWithoutCodeGen {
	public static void main(String[] args) {
		try {
			Schema schema = new Schema.Parser().parse(new File("src/main/avro/avroSample.avsc"));
			
			readTextWriteAVRO(schema);
			
			readAVROWriteText(schema);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readTextWriteAVRO(Schema schema) {
		BufferedReader bufferReader;
		try {
			bufferReader = new BufferedReader(new FileReader("Employee.txt"));
			DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
			DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
			dataFileWriter.create(schema, new File("WithoutCodeGenWriteAVRO.avro"));
			
			GenericRecord emp1 = new GenericData.Record(schema);
			
			String line = null;
			while ((line = bufferReader.readLine()) != null)   {
				String [] entries = line.split(",");
				
				emp1.put("id", Integer.parseInt(entries[0]));
				emp1.put("name", entries[1]);
				emp1.put("designation", entries[2]);
				emp1.put("mgrid", Integer.parseInt(entries[3]));
				emp1.put("hiredate", entries[4]);
				emp1.put("salary", Double.parseDouble(entries[5]));
				emp1.put("commission", Double.parseDouble(entries[6]));
				emp1.put("deptid", Integer.parseInt(entries[7]));
				dataFileWriter.append(emp1);
			}
			dataFileWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public static void readAVROWriteText(Schema schema) {
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		DataFileReader<GenericRecord> dataFileReader;
		try {
			dataFileReader = new DataFileReader<GenericRecord>(new File("WithoutCodeGenWriteAVRO.avro"), datumReader);
			GenericRecord emp = null;
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("WithoutCodeGenWriteText.txt"));
			
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
