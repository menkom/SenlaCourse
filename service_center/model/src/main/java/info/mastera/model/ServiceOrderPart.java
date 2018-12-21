package info.mastera.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import info.mastera.model.base.BaseObject;

@Entity
@Table(name = "service_order_part", schema = "service_center")
public class ServiceOrderPart extends BaseObject {

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Basic
    @Column(name = "final_price", nullable = false)
    private Double finalPrice;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "service_order_id")
    private ServiceOrder serviceOrder;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @Override
    public boolean equals(Object other) {
        ServiceOrderPart serviceOrderPart = (ServiceOrderPart) other;
        return super.equals(other)
                && isEqual(finalPrice, serviceOrderPart.getFinalPrice())
                && isEqual(user, serviceOrderPart.getUser())
                && isEqual(warehouse, serviceOrderPart.getWarehouse())
                && isEqual(serviceOrder, serviceOrderPart.getServiceOrder());
    }

    @Override
    public int hashCode() {
        int result = objHash(super.hashCode(), finalPrice);
        result = objHash(result, user);
        result = objHash(result, warehouse);
        return objHash(result, serviceOrder);
    }

}
