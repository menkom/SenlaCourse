package info.mastera.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import info.mastera.model.base.BaseObject;

@Entity
@Table(name = "warehouse", schema = "service_center")
public class Warehouse extends BaseObject {

	@Basic
	@Column(name = "code", nullable = false, unique = true)
	private String code;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "part_id", nullable = false)
	private Part part;

	@Basic
	@Column(name = "count", nullable = false)
	private Double count;

	@Basic
	@Column(name = "price_per_one", nullable = false)
	private Double pricePerOne;

	@Basic
	@Column(name = "descr", nullable = false)
	private String descr;

	@OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceOrderPart> serviceOrderParts;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public Double getPricePerOne() {
		return pricePerOne;
	}

	public void setPricePerOne(Double pricePerOne) {
		this.pricePerOne = pricePerOne;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public List<ServiceOrderPart> getServiceOrderParts() {
		return serviceOrderParts;
	}

	public void setServiceOrderParts(List<ServiceOrderPart> serviceOrderParts) {
		this.serviceOrderParts = serviceOrderParts;
	}

}
