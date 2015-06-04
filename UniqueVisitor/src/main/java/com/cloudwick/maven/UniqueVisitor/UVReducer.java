package com.cloudwick.maven.UniqueVisitor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UVReducer extends Reducer<Text, Text, Text, IntWritable>{
	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1,
			Reducer<Text, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		Set<String> uniqUsers = new HashSet<String>();
		for (Text user : arg1) {
			uniqUsers.add(user.toString());
		}
		context.write(arg0, new IntWritable(uniqUsers.size()));
	}
}
