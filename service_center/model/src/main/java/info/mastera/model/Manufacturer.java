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
@Table(name = "manufacturer", schema = "service_center")
public class Manufacturer extends BaseObject {

	@Basic
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Product> products;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
