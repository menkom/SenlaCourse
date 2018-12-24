package info.mastera.beans.orderjob;

import info.mastera.beans.base.BaseBean;
import info.mastera.model.OrderJob;
import info.mastera.service.IManufacturerService;
import info.mastera.service.IOrderJobService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class OrderJobBean extends BaseBean {

    private static final String URL_PAGE = "/views/orderJob/orderJobPageList.xhtml?i=3";

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

    public String cancel() {
        clearForm();
        return URL_PAGE;
    }

    private void clearForm() {
        name = null;
        code = null;
        price = 0;
    }

    public String create() {
        OrderJob orderJob = new OrderJob();
        orderJob.setCode(this.code);
        orderJob.setName(this.name);
        orderJob.setPrice(this.price);
        orderJobService.create(orderJob);
        clearForm();
        return URL_PAGE;
    }

}
