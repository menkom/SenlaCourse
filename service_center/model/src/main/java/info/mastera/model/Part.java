package info.mastera.model;

import info.mastera.model.base.BaseObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "part", schema = "service_center")
public class Part extends BaseObject {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "part_category_id", nullable = false)
    private PartCategory partCategory;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "part", cascade = CascadeType.MERGE, orphanRemoval = true)
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

    @Override
    public boolean equals(Object other) {
        Part part = (Part) other;
        return super.equals(other)
                && isEqual(name, part.getName())
                && isEqual(product, part.getProduct())
                && isEqual(partCategory, part.getPartCategory());
    }

    @Override
    public int hashCode() {
        int result = objHash(super.hashCode(), name);
        result = objHash(result, product);
        return objHash(result, partCategory);
    }

}
