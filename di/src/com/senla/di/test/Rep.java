package com.senla.di.test;

public class Rep extends Base implements IRep {

	String className;

//	@Override
	public void read() {
		System.out.println("Class:" + this.getClass().getSimpleName() + "; .read(); ");
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
