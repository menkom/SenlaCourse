package info.mastera.beans.serviceorder;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.ServiceOrder;
import info.mastera.service.IServiceOrderService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ServiceOrderListBean  extends BaseListBean<ServiceOrder> {


    @Inject
    private IServiceOrderService serviceOrderService;

    @Override
    public List<ServiceOrder> getAll() {
        return serviceOrderService.getAll();
    }

    @Override
    public void delete() {
        serviceOrderService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

    @Override
    public void update() {
        serviceOrderService.update(getSelectedItem());
    }

}
