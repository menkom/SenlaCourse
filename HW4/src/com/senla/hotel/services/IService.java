package com.senla.hotel.services;

import com.senla.base.BaseObject;
import com.senla.hotel.array.IBaseArray;

public interface IService {

	public void add(BaseObject element);

	public IBaseArray getRepository();

	public String[] getStringToSave();

}
