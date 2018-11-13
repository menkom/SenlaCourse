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
@Table(name = "part", schema = "service_center")
public class Part extends BaseObject {
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "part_category_id", nullable = false)
	private PartCategory partCategory;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Basic
	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Warehouse> warehouses;

	public PartCategory getPartCategory() {
		return partCategory;
	}

	public void setPartCategory(PartCategory partCategory) {
		this.partCategory = partCategory;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

}
