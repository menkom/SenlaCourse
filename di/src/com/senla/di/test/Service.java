package com.senla.di.test;

import com.senla.di.DependencyInjection;

public class Service extends Base implements IService {

	private IRep repository;

	public Service() {
		repository = (IRep) DependencyInjection.getInstance().getInterfacePair(IRep.class);
	}

	public IRep getRepository() {
		System.out.println("Class:" + this.getClass().getSimpleName() + "; .getRepository();");
		for (Class<?> interfaceClass : this.getClass().getInterfaces()) {
			System.out.println("interface:" + interfaceClass);
		}
		return repository;
	}

	public void setRepository(IRep repository) {
		System.out.println("Class:" + this.getClass().getSimpleName() + "; .setRepository();");
		this.repository = repository;
	}

}
