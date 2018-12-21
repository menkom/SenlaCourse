package info.mastera.model;

import info.mastera.model.base.BaseObject;

import javax.persistence.*;
import java.util.List;

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

    @Override
    public boolean equals(Object other) {
        Manufacturer manufacturer = (Manufacturer) other;
        return super.equals(other)
                && isEqual(name, manufacturer.getName());
    }

    @Override
    public int hashCode() {
        return objHash(super.hashCode(), name);
    }
}
