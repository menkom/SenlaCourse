package model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.base.BaseObject;

@Entity
@Table(name = "customer", schema = "service_center")
public class Customer extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Basic
	@Column(name = "customer_name")
	private String customerName;

	@Basic
	@Column(name = "telephone")
	private String telephone;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceOrder> serviceOrders;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> users;

	@Override
	public Integer getId() {
		return id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ServiceOrder> getServiceOrders() {
		return serviceOrders;
	}

	public void setServiceOrders(List<ServiceOrder> serviceOrders) {
		this.serviceOrders = serviceOrders;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
