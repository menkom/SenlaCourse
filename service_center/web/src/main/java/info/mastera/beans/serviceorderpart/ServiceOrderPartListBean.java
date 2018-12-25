package info.mastera.beans.serviceorderpart;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.ServiceOrderPart;
import info.mastera.service.IServiceOrderPartService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ServiceOrderPartListBean  extends BaseListBean<ServiceOrderPart> {


    @Inject
    private IServiceOrderPartService serviceOrderPartService;

    @Override
    public List<ServiceOrderPart> getAll() {
        return serviceOrderPartService.getAll();
    }

    @Override
    public void delete() {
        serviceOrderPartService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

}
