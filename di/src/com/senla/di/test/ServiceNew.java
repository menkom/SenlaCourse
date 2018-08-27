package com.senla.di.test;

import com.senla.di.DependencyInjection;

public class ServiceNew extends Base implements IService {

	@Override
	public IRep getRepository() {
		IRep rep = (IRep) DependencyInjection.getInstance().getInstance(IRep.class);
		System.out.println("Class:" + this.getClass().getSimpleName() + "; .getRepository();");
		for (Class<?> interfaceClass : this.getClass().getInterfaces()) {
			System.out.println("interface:" + interfaceClass);
		}
		return rep;
	}

	@Override
	public void setRepository(IRep rep) {
		System.out.println("There is only method. No Action.");
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("--This New Service for testing DI.--");
	}

}
