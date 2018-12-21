package info.mastera.model;

import info.mastera.model.base.BaseObject;
import info.mastera.model.enums.RepairStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "service_order", schema = "service_center")
public class ServiceOrder extends BaseObject {

    @Enumerated(EnumType.STRING)
    @Column(name = "repair_status", nullable = false)
    private RepairStatus repairStatus;

    @Basic
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "date_order")
    private Date dateOrder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Basic
    @Column(name = "check_serial")
    private Boolean checkSerial;

    @Basic
    @Column(name = "serial")
    private String serial;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Basic
    @Column(name = "client_fault")
    private String clientFault;

    @Basic
    @Column(name = "comment", length = 500)
    private String comment;

    @Basic
    @Column(name = "engineer_descr")
    private String engineerDescr;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "date_expected")
    private Date dateExpected;

    @Basic
    @Column(name = "expected_cost", nullable = false)
    private Double expectedCost;

    @Basic
    @Column(name = "prepaid", nullable = false)
    private Double prepaid;

    @Basic
    @Column(name = "final_price", nullable = false)
    private Double finalPrice;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "date_finish")
    private Date dateFinish;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "date_warranty")
    private Date dateWarranty;

    @OneToMany(mappedBy = "serviceOrder", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ServiceOrderJob> jobs;

    @OneToMany(mappedBy = "serviceOrder", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ServiceOrderPart> parts;

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

    public Boolean getCheckSerial() {
        return checkSerial;
    }

    public void setCheckSerial(Boolean checkSerial) {
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

    public Double getExpectedCost() {
        return expectedCost;
    }

    public void setExpectedCost(Double expectedCost) {
        this.expectedCost = expectedCost;
    }

    public Double getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(Double prepaid) {
        this.prepaid = prepaid;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
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

    public List<ServiceOrderJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<ServiceOrderJob> jobs) {
        this.jobs = jobs;
    }

    public List<ServiceOrderPart> getParts() {
        return parts;
    }

    public void setParts(List<ServiceOrderPart> parts) {
        this.parts = parts;
    }

    @Override
    public boolean equals(Object other) {
        ServiceOrder serviceOrder = (ServiceOrder) other;
        return super.equals(other)
                && isEqual(repairStatus, serviceOrder.getRepairStatus())
                && isEqual(checkSerial, serviceOrder.getCheckSerial())

                && isEqual(expectedCost, serviceOrder.getExpectedCost())
                && isEqual(prepaid, serviceOrder.getPrepaid())
                && isEqual(finalPrice, serviceOrder.getFinalPrice())

                && isEqual(dateExpected, serviceOrder.getDateExpected())
                && isEqual(dateFinish, serviceOrder.getDateFinish())
                && isEqual(dateWarranty, serviceOrder.getDateWarranty())
                && isEqual(dateOrder, serviceOrder.getDateOrder())

                && isEqual(code, serviceOrder.getCode())
                && isEqual(serial, serviceOrder.getSerial())
                && isEqual(clientFault, serviceOrder.getClientFault())
                && isEqual(comment, serviceOrder.getComment())
                && isEqual(engineerDescr, serviceOrder.getEngineerDescr())

                && isEqual(customer, serviceOrder.getCustomer())
                && isEqual(product, serviceOrder.getProduct());
    }

    @Override
    public int hashCode() {
        int result = objHash(super.hashCode(), repairStatus);
        result = objHash(result, checkSerial);
        result = objHash(result, expectedCost);
        result = objHash(result, prepaid);
        result = objHash(result, finalPrice);
        result = objHash(result, dateExpected);
        result = objHash(result, dateFinish);
        result = objHash(result, dateWarranty);
        result = objHash(result, dateOrder);
        result = objHash(result, code);
        result = objHash(result, serial);
        result = objHash(result, clientFault);
        result = objHash(result, comment);
        result = objHash(result, engineerDescr);
        result = objHash(result, customer);
        return objHash(result, product);
    }

}
