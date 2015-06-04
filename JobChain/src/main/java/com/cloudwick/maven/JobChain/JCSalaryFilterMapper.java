package com.cloudwick.maven.JobChain;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class JCSalaryFilterMapper extends Mapper<Text, Text, Text, Text>{
	@Override
	protected void map(Text key, Text value,
			Mapper<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String record = value.toString();
		
		Configuration conf = context.getConfiguration();
		String minSal = conf.get("minSalary");
		String maxSal = conf.get("maxSalary");
		
		if(Integer.parseInt(record) > Integer.parseInt(minSal) && Integer.parseInt(record) < Integer.parseInt(maxSal)) {
			String [] entries = key.toString().split(",");
			context.write(new Text(entries[0]), new Text(entries[1] + "," + entries[2] + "," + record));
		}
	}
}
