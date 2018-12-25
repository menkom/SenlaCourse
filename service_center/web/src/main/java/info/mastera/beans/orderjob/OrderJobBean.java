package info.mastera.beans.orderjob;

import info.mastera.beans.base.BaseBean;
import info.mastera.model.OrderJob;
import info.mastera.service.IOrderJobService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class OrderJobBean extends BaseBean {

    @Inject
    private IOrderJobService orderJobService;

    private String name;
    private String code;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private void setFields(OrderJob orderJob) {
        if (orderJob != null) {
            clearForm();
            setId(orderJob.getId());
            code = orderJob.getCode();
            name = orderJob.getName();
            price = orderJob.getPrice();
        }
    }

    public void update() {
        OrderJob orderJob = new OrderJob();
        orderJob.setId(getId());
        orderJob.setCode(code);
        orderJob.setName(name);
        orderJob.setPrice(price);
        orderJobService.update(orderJob);
    }

    public void load() {
        if (getId() != null) {
            OrderJob orderJob = orderJobService.getById(getId());
            setFields(orderJob);
        }
    }

    public void clearForm() {
        setId(null);
        name = null;
        code = null;
        price = 0;
    }

    public void create() {
        OrderJob orderJob = new OrderJob();
        orderJob.setCode(this.code);
        orderJob.setName(this.name);
        orderJob.setPrice(this.price);
        orderJobService.create(orderJob);
    }

}
