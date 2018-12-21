package info.mastera.model;

import info.mastera.model.base.BaseObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "part_category", schema = "service_center")
public class PartCategory extends BaseObject {

    @Basic
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "partCategory", cascade = CascadeType.MERGE, orphanRemoval = true)
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

    @Override
    public boolean equals(Object other) {
        PartCategory partCategory = (PartCategory) other;
        return super.equals(other)
                && isEqual(name, partCategory.getName());
    }

    @Override
    public int hashCode() {
        return objHash(super.hashCode(), name);
    }

}
