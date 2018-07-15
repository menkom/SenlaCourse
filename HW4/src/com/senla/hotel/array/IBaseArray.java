package com.senla.hotel.array;

import com.senla.base.BaseObject;

public interface IBaseArray {

	public void add(BaseObject element);

	public void delete(BaseObject element);

	public String[] toStringArray();

	public void setArray(BaseObject[] array) ;

}
