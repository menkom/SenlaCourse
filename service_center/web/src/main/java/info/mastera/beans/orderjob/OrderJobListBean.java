package info.mastera.beans.orderjob;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.OrderJob;
import info.mastera.service.IOrderJobService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class OrderJobListBean extends BaseListBean<OrderJob> {

    @Inject
    private IOrderJobService orderJobService;

    @Override
    public List<OrderJob> getAll() {
        return orderJobService.getAll();
    }

    @Override
    public void delete() {
        orderJobService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

}
