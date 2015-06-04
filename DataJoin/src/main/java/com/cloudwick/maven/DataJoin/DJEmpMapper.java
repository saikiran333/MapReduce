package com.cloudwick.maven.DataJoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DJEmpMapper extends Mapper<LongWritable, Text, IntWritable, Text>{
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		String[] words = value.toString().split(",");
		context.write(new IntWritable(Integer.parseInt(words[2])), new Text(words[0] + "," + words[1]));
	}
}
