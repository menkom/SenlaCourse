package info.mastera.beans.warehouse;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Warehouse;
import info.mastera.service.IWarehouseService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class WarehouseListBean  extends BaseListBean<Warehouse> {


    @Inject
    private IWarehouseService warehouseService;

    @Override
    public List<Warehouse> getAll() {
        return warehouseService.getAll();
    }

    @Override
    public void delete() {
        warehouseService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

    @Override
    public void update() {
        warehouseService.update(getSelectedItem());
    }

}
