package info.mastera.model;

import info.mastera.model.base.BaseObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "service_order_job", schema = "service_center")
public class ServiceOrderJob extends BaseObject {

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_job_id", nullable = false)
    private OrderJob orderJob;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Basic
    @Column(name = "final_price", nullable = false)
    private Double finalPrice;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id")
    private ServiceOrder serviceOrder;

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

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((!(o instanceof ServiceOrderJob)) || (!super.equals(o))) {
            return false;
        }
        ServiceOrderJob that = (ServiceOrderJob) o;
        return Objects.equals(getFinalPrice(), that.getFinalPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFinalPrice());
    }
}
