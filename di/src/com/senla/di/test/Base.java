package com.senla.di.test;

public class Base implements IBase {

	@Override
	public void printInfo() {
		System.out.println("Class:" + this.getClass().getSimpleName() + "; .getInfo();");
		for (Class<?> interfaceClass : this.getClass().getInterfaces()) {
			System.out.println("interface:" + interfaceClass);
		}
	}

}
