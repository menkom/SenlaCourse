package info.mastera.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import info.mastera.model.base.BaseObject;
import info.mastera.model.enums.UserType;

@Entity
@Table(name = "user", schema = "service_center")
public class User extends BaseObject {

	@Basic
	@Column(name = "username")
	private String username;

	@Basic
	@Column(name = "password")
	private String password;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type", nullable = false)
	private UserType userType;

	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<ServiceOrderJob> jobs;

	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<ServiceOrderPart> serviceOrderParts;

	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<UserLoginHistory> userLoginHistories;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public List<ServiceOrderJob> getJobs() {
		return jobs;
	}

	public void setJobs(List<ServiceOrderJob> jobs) {
		this.jobs = jobs;
	}

	public List<ServiceOrderPart> getServiceOrderParts() {
		return serviceOrderParts;
	}

	public void setServiceOrderParts(List<ServiceOrderPart> serviceOrderParts) {
		this.serviceOrderParts = serviceOrderParts;
	}

	public List<UserLoginHistory> getUserLoginHistories() {
		return userLoginHistories;
	}

	public void setUserLoginHistories(List<UserLoginHistory> userLoginHistories) {
		this.userLoginHistories = userLoginHistories;
	}

}
