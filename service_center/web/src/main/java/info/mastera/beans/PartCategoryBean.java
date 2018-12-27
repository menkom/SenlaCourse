package info.mastera.beans;

import info.mastera.beans.base.BaseBean;
import info.mastera.beans.base.BaseListBean;
import info.mastera.model.PartCategory;
import info.mastera.service.IManufacturerService;
import info.mastera.service.IPartCategoryService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class PartCategoryBean extends BaseListBean<PartCategory> {

    @Inject
    private IPartCategoryService partCategoryService;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    private void setFields(PartCategory partCategory) {
        if (partCategory != null) {
            clearForm();
            setId(partCategory.getId());
            name = partCategory.getName();
        }
    }

    public void update() {
        PartCategory partCategory = new PartCategory();
        partCategory.setId(getId());
        partCategory.setName(name);
        partCategoryService.update(partCategory);
    }

    public void load() {
        if (getId() != null) {
            PartCategory partCategory = partCategoryService.getById(getId());
            setFields(partCategory);
        }
    }

    public void clearForm() {
        setId(null);
        name = null;
    }

    public void create() {
        PartCategory partCategory = new PartCategory();
        partCategory.setName(this.name);
        partCategoryService.create(partCategory);
    }

}
