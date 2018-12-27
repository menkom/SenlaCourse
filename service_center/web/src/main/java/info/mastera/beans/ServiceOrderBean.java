package info.mastera.beans;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Customer;
import info.mastera.model.Product;
import info.mastera.model.ServiceOrder;
import info.mastera.model.ServiceOrderJob;
import info.mastera.model.enums.RepairStatus;
import info.mastera.service.IServiceOrderService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class ServiceOrderBean extends BaseListBean<ServiceOrder> {

    @Inject
    private IServiceOrderService serviceOrderService;

    private RepairStatus repairStatus;
    private String code;
    private Date dateOrder;
    private Product product;
    private boolean checkSerial;
    private String serial;
    private Customer customer;
    private String clientFault;
    private String comment;
    private String engineerDescr;
    private Date dateExpected;
    private double expectedCost;
    private double prepaid;
    private double finalPrice;
    private Date dateFinish;
    private Date dateWarranty;

    private List<ServiceOrderJob> serviceOrderJobs;

    public RepairStatus getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(RepairStatus repairStatus) {
        this.repairStatus = repairStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isCheckSerial() {
        return checkSerial;
    }

    public void setCheckSerial(boolean checkSerial) {
        this.checkSerial = checkSerial;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getClientFault() {
        return clientFault;
    }

    public void setClientFault(String clientFault) {
        this.clientFault = clientFault;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEngineerDescr() {
        return engineerDescr;
    }

    public void setEngineerDescr(String engineerDescr) {
        this.engineerDescr = engineerDescr;
    }

    public Date getDateExpected() {
        return dateExpected;
    }

    public void setDateExpected(Date dateExpected) {
        this.dateExpected = dateExpected;
    }

    public double getExpectedCost() {
        return expectedCost;
    }

    public void setExpectedCost(double expectedCost) {
        this.expectedCost = expectedCost;
    }

    public double getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(double prepaid) {
        this.prepaid = prepaid;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getDateWarranty() {
        return dateWarranty;
    }

    public void setDateWarranty(Date dateWarranty) {
        this.dateWarranty = dateWarranty;
    }

    public List<ServiceOrderJob> getServiceOrderJobs() {
        return serviceOrderJobs;
    }

    public void setServiceOrderJobs(List<ServiceOrderJob> serviceOrderJobs) {
        this.serviceOrderJobs = serviceOrderJobs;
    }

    @Override
    public List<ServiceOrder> getAll() {
        return serviceOrderService.getAllWithProductAndCustomer();
    }

    @Override
    public void delete() {
        serviceOrderService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }


    private void setFields(ServiceOrder serviceOrder) {
        if (serviceOrder != null) {
            clearForm();
            setId(serviceOrder.getId());
            setRepairStatus(serviceOrder.getRepairStatus());
            setCode(serviceOrder.getCode());
            setDateOrder(serviceOrder.getDateOrder());
            setProduct(serviceOrder.getProduct());
            setCheckSerial(serviceOrder.getCheckSerial());
            setSerial(serviceOrder.getSerial());
            setCustomer(serviceOrder.getCustomer());
            setClientFault(serviceOrder.getClientFault());
            setComment(serviceOrder.getComment());
            setEngineerDescr(serviceOrder.getEngineerDescr());
            setDateExpected(serviceOrder.getDateExpected());
            setExpectedCost(serviceOrder.getExpectedCost());
            setPrepaid(serviceOrder.getPrepaid());
            setFinalPrice(serviceOrder.getFinalPrice());
            setDateFinish(serviceOrder.getDateFinish());
            setDateWarranty(serviceOrder.getDateWarranty());
        }
    }

    public void update() {
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setId(getId());
        serviceOrder.setRepairStatus(getRepairStatus());
        serviceOrder.setCode(getCode());
        serviceOrder.setDateOrder(getDateOrder());
        serviceOrder.setProduct(getProduct());
        serviceOrder.setCheckSerial(isCheckSerial());
        serviceOrder.setSerial(getSerial());
        serviceOrder.setCustomer(getCustomer());
        serviceOrder.setClientFault(getClientFault());
        serviceOrder.setComment(getComment());
        serviceOrder.setEngineerDescr(getEngineerDescr());
        serviceOrder.setDateExpected(getDateExpected());
        serviceOrder.setExpectedCost(getExpectedCost());
        serviceOrder.setPrepaid(getPrepaid());
        serviceOrder.setFinalPrice(getFinalPrice());
        serviceOrder.setDateFinish(getDateFinish());
        serviceOrder.setDateWarranty(getDateWarranty());

        serviceOrderService.update(serviceOrder);
    }

    public void load() {
        if (getId() != null) {
            ServiceOrder serviceOrder = serviceOrderService.getByIdWithProductAndCutomer(getId());
            setFields(serviceOrder);
        }
    }

    public void clearForm() {
        setId(null);
        setRepairStatus(null);
        setCode(null);
        setDateOrder(null);
        setProduct(null);
        setCheckSerial(true);
        setSerial(null);
        setCustomer(null);
        setClientFault(null);
        setComment(null);
        setEngineerDescr(null);
        setDateExpected(null);
        setExpectedCost(0);
        setPrepaid(0);
        setFinalPrice(0);
        setDateFinish(null);
        setDateWarranty(null);
    }

    public void create() {
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setId(getId());
        serviceOrder.setRepairStatus(getRepairStatus());
        serviceOrder.setCode(getCode());
        serviceOrder.setDateOrder(getDateOrder());
        serviceOrder.setProduct(getProduct());
        serviceOrder.setCheckSerial(isCheckSerial());
        serviceOrder.setSerial(getSerial());
        serviceOrder.setCustomer(getCustomer());
        serviceOrder.setClientFault(getClientFault());
        serviceOrder.setComment(getComment());
        serviceOrder.setEngineerDescr(getEngineerDescr());
        serviceOrder.setDateExpected(getDateExpected());
        serviceOrder.setExpectedCost(getExpectedCost());
        serviceOrder.setPrepaid(getPrepaid());
        serviceOrder.setFinalPrice(getFinalPrice());
        serviceOrder.setDateFinish(getDateFinish());
        serviceOrder.setDateWarranty(getDateWarranty());
        serviceOrderService.create(serviceOrder);
    }

}
