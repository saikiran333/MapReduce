package com.cloudwick.maven.MultiMapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MPMapper1 extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String record = value.toString();
		if(record.contains("AZ")) {
			String [] entries = record.split(",");
			context.write(new Text(entries[0] + "       " + entries[2]), new Text(entries[1]));
		}

	}
}
