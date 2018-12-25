package info.mastera.beans.partcategory;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.PartCategory;
import info.mastera.service.IPartCategoryService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class PartCategoryListBean  extends BaseListBean<PartCategory> {

    @Inject
    private IPartCategoryService partCategoryService;

    @Override
    public List<PartCategory> getAll() {
        return partCategoryService.getAll();
    }

    @Override
    public void delete() {
        partCategoryService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

}
