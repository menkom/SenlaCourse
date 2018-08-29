package com.senla.di.test;

import com.senla.di.DependencyInjection;

public class Facade extends Base implements IFacade {

	private IService service;

	public Facade() {
		service = (IService) DependencyInjection.getInstance().getInterfacePair(IService.class);
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
