package com.cloudwick.maven.JoinMR;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class NaturalKeyGroupingComparator extends WritableComparator{
	protected NaturalKeyGroupingComparator() {
		super(MapperCompositeKey.class, true);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		MapperCompositeKey k1 = (MapperCompositeKey)w1;
		MapperCompositeKey k2 = (MapperCompositeKey)w2;
		
		return k1.getDeptId().compareTo(k2.getDeptId());
	}
}
