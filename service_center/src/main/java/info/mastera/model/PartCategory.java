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
@Table(name = "part_category", schema = "service_center")
public class PartCategory extends BaseObject {

	@Basic
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "partCategory", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part> parts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

}
