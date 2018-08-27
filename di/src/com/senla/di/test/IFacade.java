package com.senla.di.test;

public interface IFacade extends IBase {

	IService getService();

	void setService(IService service);

}
