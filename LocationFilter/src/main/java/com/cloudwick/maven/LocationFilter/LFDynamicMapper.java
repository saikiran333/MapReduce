package com.cloudwick.maven.LocationFilter;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LFDynamicMapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		String loc = conf.get("location");
		String record = value.toString();
		if(record.contains(loc)) {
			String [] entries = record.split(",");
			context.write(new Text(entries[0] + "       " + entries[2]), new Text(entries[1]));
		}

	}
	
	
}
