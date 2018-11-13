package info.mastera.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import info.mastera.model.base.BaseObject;

@Entity
@Table(name = "order_job", schema = "service_center")
public class OrderJob extends BaseObject {

	@Basic
	@Column(name = "code", nullable = false, unique = true)
	private String code;

	@Basic
	@Column(name = "name", nullable = false)
	private String name;

	@Basic
	@Column(name = "price", nullable = false)
	private Double Price;

	@OneToMany(mappedBy = "orderJob", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceOrderJob> serviceOrderJobs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public List<ServiceOrderJob> getServiceOrderJobs() {
		return serviceOrderJobs;
	}

	public void setServiceOrderJobs(List<ServiceOrderJob> serviceOrderJobs) {
		this.serviceOrderJobs = serviceOrderJobs;
	}

}
