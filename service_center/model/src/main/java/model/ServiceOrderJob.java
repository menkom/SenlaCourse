package model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.base.BaseObject;

@Entity
@Table(name = "service_order_job", schema = "service_center")
public class ServiceOrderJob extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_job_id", nullable = false)
	private OrderJob orderJob;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Basic
	@Column(name = "final_price", nullable = false)
	private Double finalPrice;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "service_order_id")
	private ServiceOrder serviceOrder;

	@Override
	public Integer getId() {
		return id;
	}

	public OrderJob getOrderJob() {
		return orderJob;
	}

	public void setOrderJob(OrderJob orderJob) {
		this.orderJob = orderJob;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

}
