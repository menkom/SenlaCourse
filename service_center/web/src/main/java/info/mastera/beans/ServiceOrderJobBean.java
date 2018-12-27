package info.mastera.beans.serviceorderjob;

import info.mastera.beans.base.BaseBean;
import info.mastera.beans.base.BaseListBean;
import info.mastera.model.ServiceOrderJob;
import info.mastera.service.IManufacturerService;
import info.mastera.service.IServiceOrderJobService;
import info.mastera.service.IServiceOrderService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ServiceOrderJobBean extends BaseListBean<ServiceOrderJob> {

    @Inject
    private IServiceOrderJobService serviceOrderJobService;

    @Override
    public List<ServiceOrderJob> getAll() {
        return serviceOrderJobService.getAll();
    }

    @Override
    public void delete() {
        serviceOrderJobService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }
}
