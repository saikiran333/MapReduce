package com.cloudwick.maven.JoinMR;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class MapperCompositeKey implements WritableComparable<MapperCompositeKey> {

	private String deptId;
	private String index;
	
	public MapperCompositeKey() {
		this.deptId = null;
		this.index = null;
	}
	
	public MapperCompositeKey(String deptId, String index) {
		this.deptId = deptId;
		this.index = index;
	}

	public void readFields(DataInput in) throws IOException {
		this.deptId = WritableUtils.readString(in);
		this.index = WritableUtils.readString(in);
	}

	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, deptId);
		WritableUtils.writeString(out, index);
	}

	public int compareTo(MapperCompositeKey pop) {
		if (pop == null)
			return 0;
		int intcnt = deptId.compareTo(pop.deptId);
		return intcnt == 0 ? index.compareTo(pop.index) : intcnt;
	}
	@Override
	public String toString() {
	return deptId.toString() + ":" + index.toString();
	}

	public String getDeptId() {
		return deptId;
	}

	public String getIndex() {
		return index;
	}

}
