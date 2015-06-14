package com.cloudwick.maven.DistributedCacheDataJoin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DCMapper extends Mapper<LongWritable, Text, IntWritable, Text>{
	
	private static HashMap<String, String> DepartmentMap = new HashMap<String, String>();
	
	@SuppressWarnings("resource")
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		Path[] cacheFilesLocal = DistributedCache.getLocalCacheFiles(context.getConfiguration());
		BufferedReader brReader = new BufferedReader(new FileReader(cacheFilesLocal[0].toString()));
		String strLineRead = "";
		while ((strLineRead = brReader.readLine()) != null) {
			String deptEntries[] = strLineRead.split(",");
			DepartmentMap.put(deptEntries[0], deptEntries[1]);
		}
	}
	
	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		String[] words = value.toString().split(",");
		String deptName = DepartmentMap.get(words[2]);
		context.write(new IntWritable(Integer.parseInt(words[0])), new Text(words[1] + "," + deptName));
	}
}
