package com.senla.di.test;

import com.senla.di.DependencyInjection;
import com.senla.di.annotation.Injection;

public class Facade extends Base implements IFacade {

	@Injection
	private IService service;

	public Facade() {
		service = (IService) DependencyInjection.getInstance().getInstance(IService.class);
	}

	public IService getService() {
		System.out.println("Class:" + this.getClass().getSimpleName() + "; .getService();");
		for (Class<?> interfaceClass : this.getClass().getInterfaces()) {
			System.out.println("interface:" + interfaceClass);
		}
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}

}
