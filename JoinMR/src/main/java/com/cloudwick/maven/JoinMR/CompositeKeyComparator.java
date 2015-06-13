package com.cloudwick.maven.JoinMR;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CompositeKeyComparator extends WritableComparator{
	protected CompositeKeyComparator() {
		super(MapperCompositeKey.class, true);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		MapperCompositeKey k1 = (MapperCompositeKey)w1;
		MapperCompositeKey k2 = (MapperCompositeKey)w2;
		
		int result = k1.getDeptId().compareTo(k2.getDeptId());
		if(0 == result) {
			result = k1.getIndex().compareTo(k2.getIndex());
		}
		return result;
	}
}
