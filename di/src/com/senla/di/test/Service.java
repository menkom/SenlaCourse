package com.senla.di.test;

import com.senla.di.DependencyInjection;
import com.senla.di.annotation.Injection;

public class Service extends Base implements IService {

	@Injection
	private IRep repository;

	public Service() {
		repository = (IRep) DependencyInjection.getInstance().getInstance(IRep.class);
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
