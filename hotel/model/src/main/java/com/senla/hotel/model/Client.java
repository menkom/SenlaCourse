package com.senla.hotel.model;

import com.senla.annotation.CsvEntity;
import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;
import com.senla.base.BaseObject;
import javax.persistence.*;

@Entity
@Table(name = "client", schema = "hotel", uniqueConstraints = @UniqueConstraint(columnNames = "client_id"))
@CsvEntity(filename = "client.csv")
public class Client extends BaseObject {

	@Id
	@Column(name = "client_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 0)
	private Integer id;

	@Basic
	@Column(name = "client_name", nullable = false, length = 50)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private String name;

	public Client() {
		super();
	}

	public Client(String name) {
		super();
		this.name = name;
	}

	@Basic
	@Column(name = "client_name", nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(super.toString()).append(getName()).append(SEPARATOR);

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
