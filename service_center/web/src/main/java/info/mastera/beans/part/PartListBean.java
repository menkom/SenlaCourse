package info.mastera.beans.part;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Customer;
import info.mastera.model.Part;
import info.mastera.service.IPartService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class PartListBean extends BaseListBean<Part> {

    @Inject
    private IPartService partService;

    @Override
    public List<Part> getAll() {
        return partService.getAll();
    }

    @Override
    public void delete() {
        partService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

}
