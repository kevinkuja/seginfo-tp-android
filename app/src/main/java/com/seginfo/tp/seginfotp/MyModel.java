package com.seginfo.tp.seginfotp;

public class MyModel {
	
	private Integer value;
	
	public MyModel() {
		value = Integer.valueOf(0);
	}

	public void setValue() {
		++value;
	}

	public Integer getValue() {
		return value;
	}
}