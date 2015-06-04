package com.cloudwick.maven.DataJoin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DJReducer extends Reducer<IntWritable, Text, IntWritable, Text>{
	@Override
	protected void reduce(IntWritable key, Iterable<Text> values,
			Reducer<IntWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		List<String> records = new ArrayList<String>();
		String deptName = null;
		for (Text user : values) {
			if(user.toString().contains(",")) {
				records.add(user.toString());
			}
			else {
				deptName = user.toString();
			}
		}
		for (String record : records) {
			String [] splits = record.split(",");
			context.write(new IntWritable(Integer.parseInt(splits[0])), new Text(splits[1] + "," + deptName));
		}
		
	}
}
