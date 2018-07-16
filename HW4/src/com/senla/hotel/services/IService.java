package com.senla.hotel.services;

import com.senla.base.BaseObject;
import com.senla.hotel.repository.IBaseRepository;

public interface IService {

	public void add(BaseObject element);

	public IBaseRepository getRepository();

	public String[] getStringToSave();

}
