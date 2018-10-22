package com.senla.hotel.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.senla.annotation.CsvProperty;
import com.senla.annotation.enums.PropertyType;
import com.senla.base.BaseObject;

@Entity
@Table(name = "service_order", schema = "hotel", uniqueConstraints = @UniqueConstraint(columnNames = "so_id"))
public class ServiceOrderJoin extends BaseObject {
	@Id
	@PrimaryKeyJoinColumn
	@Column(name = "so_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 0)
	private Integer id;

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "so_order_id", nullable = false)
	@CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 3, keyField = "id")
	private Order order;

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "so_service_id", nullable = false)
	@CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 3, keyField = "id")
	private Service service;

	@Override

	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}
