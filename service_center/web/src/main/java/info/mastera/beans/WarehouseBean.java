package info.mastera.beans;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Part;
import info.mastera.model.Warehouse;
import info.mastera.service.IWarehouseService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class WarehouseBean extends BaseListBean<Warehouse> {

    @Inject
    private IWarehouseService warehouseService;

    private String code;
    private Part part;
    private double count;
    private double pricePerOne;
    private String descr;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getPricePerOne() {
        return pricePerOne;
    }

    public void setPricePerOne(double pricePerOne) {
        this.pricePerOne = pricePerOne;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public List<Warehouse> getAll() {
        return warehouseService.getAllWithPart();
    }

    @Override
    public void delete() {
        warehouseService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

    private void setFields(Warehouse warehouse) {
        if (warehouse != null) {
            clearForm();
            setId(warehouse.getId());
            code = warehouse.getCode();
            part = warehouse.getPart();
            count = warehouse.getCount();
            pricePerOne = warehouse.getPricePerOne();
            descr = warehouse.getDescr();
        }
    }

    public void update() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(getId());
        warehouse.setCode(code);
        warehouse.setPart(part);
        warehouse.setCount(count);
        warehouse.setPricePerOne(pricePerOne);
        warehouse.setDescr(descr);
        warehouseService.update(warehouse);
    }

    public void load() {
        if (getId() != null) {
            Warehouse warehouse = warehouseService.getByIdWithPart(getId());
            setFields(warehouse);
        }
    }

    public void clearForm() {
        setId(null);
        setCode(null);
        setPart(null);
        setCount(0);
        setPricePerOne(0);
        setDescr(null);
    }

    public void create() {
        Warehouse warehouse = new Warehouse();
        warehouse.setCode(code);
        warehouse.setPart(part);
        warehouse.setCount(count);
        warehouse.setPricePerOne(pricePerOne);
        warehouse.setDescr(descr);
        warehouseService.create(warehouse);
    }

}