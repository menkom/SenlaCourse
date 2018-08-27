package com.senla.di.test;

import com.senla.di.DependencyInjection;

public class TestDIExecuter {

	public static void main(String[] args) {
		System.out.println("Class:" + TestDIExecuter.class.getSimpleName() + "; .main(); parent :"
				+ TestDIExecuter.class.getSuperclass().getSimpleName());

		IFacade facade = (IFacade) DependencyInjection.getInstance().getInstance(IFacade.class);

		facade.printInfo();
		facade.getService().printInfo();
		facade.getService().getRepository().printInfo();

	}

}
