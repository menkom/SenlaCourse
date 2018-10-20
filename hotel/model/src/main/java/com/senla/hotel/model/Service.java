package com.senla.hotel.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;
import com.senla.base.BaseObject;

@Entity
@Table(name = "service", schema = "hotel", uniqueConstraints = @UniqueConstraint(columnNames = "service_id"))
@CsvEntity(filename = "service.csv")
public class Service extends BaseObject {

	@Id
	@Column(name = "service_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 0)
	private Integer id;
	@Basic
	@Column(name = "service_code", nullable = false)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private Integer code;
	@Basic
	@Column(name = "service_name", nullable = false)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private String name;
	@Basic
	@Column(name = "service_price", nullable = false)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	private Integer price;

	public Service() {
		super();
	}

	public Service(Integer code, String name, Integer price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	@Basic
	@Column(name = "service_price", nullable = false)
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Basic
	@Column(name = "service_name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());

		builder.append(getCode()).append(SEPARATOR);
		builder.append(getName()).append(SEPARATOR);
		builder.append(getPrice()).append(SEPARATOR);

		return builder.toString();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
