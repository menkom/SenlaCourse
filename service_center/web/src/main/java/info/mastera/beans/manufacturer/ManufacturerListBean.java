package info.mastera.beans.manufacturer;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Manufacturer;
import info.mastera.service.IManufacturerService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ManufacturerListBean extends BaseListBean<Manufacturer> {

    @Inject
    private IManufacturerService manufacturerService;

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerService.getAll();
    }

    @Override
    public void delete() {
        manufacturerService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

    @Override
    public void update() {
        manufacturerService.update(getSelectedItem());
    }
}
