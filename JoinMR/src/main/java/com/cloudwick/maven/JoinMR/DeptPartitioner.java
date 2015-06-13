package com.cloudwick.maven.JoinMR;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class DeptPartitioner extends Partitioner<MapperCompositeKey, Text> {

	@Override
	public int getPartition(MapperCompositeKey key, Text value,
			int numReduceTasks) {
		return 0;
//		if (numReduceTasks == 0)
//			return 0;
//
//		if (key.getDeptId().equals("101")) {
//			return 1 % numReduceTasks;
//		}
//		else if (key.getDeptId().equals("102")) {
//
//			return 2 % numReduceTasks;
//		}
//		else if (key.getDeptId().equals("103")) {
//
//			return 3 % numReduceTasks;
//		}
//		else if (key.getDeptId().equals("104")) {
//
//			return 4 % numReduceTasks;
//		}
//		else if (key.getDeptId().equals("105")) {
//
//			return 5 % numReduceTasks;
//		}
//		else
//			return 0;
	}

}
