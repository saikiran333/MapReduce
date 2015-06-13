package com.cloudwick.maven.JoinMR;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinReducer extends Reducer<MapperCompositeKey, Text, IntWritable, Text> {
	@Override
	protected void reduce(MapperCompositeKey key, Iterable<Text> values,
			Reducer<MapperCompositeKey, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		int flag = 0;
		String deptName = "";
		for (Text record : values) {
			if(flag == 0) {
				deptName = record.toString();
				flag = 1;
			}
			else {
				String [] splits = record.toString().split(",");
				context.write(new IntWritable(Integer.parseInt(splits[0])), new Text(splits[1] + "," + deptName));
			}
		}
	}
}
