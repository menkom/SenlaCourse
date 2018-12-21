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
@Table(name = "product", schema = "service_center")
public class Product extends BaseObject {

    @Basic
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ServiceOrder> serviceOrders;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Part> parts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    public boolean equals(Object other) {
        Product product = (Product) other;
        return super.equals(other)
                && isEqual(name, product.getName())
                && isEqual(code, product.getCode())
                && isEqual(manufacturer, product.getManufacturer());
    }

    @Override
    public int hashCode() {
        int result = objHash(super.hashCode(), code);
        result = objHash(result, name);
        return objHash(result, manufacturer);
    }

}
