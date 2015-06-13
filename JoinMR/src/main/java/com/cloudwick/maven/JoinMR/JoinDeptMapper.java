package com.cloudwick.maven.JoinMR;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class JoinDeptMapper extends Mapper<LongWritable, Text, MapperCompositeKey, Text>{
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, MapperCompositeKey, Text>.Context context)
			throws IOException, InterruptedException {
		String[] words = value.toString().split(",");
		MapperCompositeKey entry = new MapperCompositeKey(words[0], "0");
		context.write(entry, new Text(words[1]));
	}
}
