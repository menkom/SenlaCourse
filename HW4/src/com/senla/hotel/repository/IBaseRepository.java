package com.senla.hotel.repository;

import com.senla.base.BaseObject;

public interface IBaseRepository {

	public void add(BaseObject element);

	public void delete(BaseObject element);

	public String[] toStringArray();

	public void setRepository(BaseObject[] array) ;

}
