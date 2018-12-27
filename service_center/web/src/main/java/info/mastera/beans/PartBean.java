package info.mastera.beans;

import info.mastera.beans.base.BaseBean;
import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Part;
import info.mastera.model.PartCategory;
import info.mastera.model.Product;
import info.mastera.service.IManufacturerService;
import info.mastera.service.IPartService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class PartBean extends BaseListBean<Part> {

    @Inject
    private IPartService partService;

    private PartCategory partCategory;
    private Product product;
    private String name;

    public PartCategory getPartCategory() {
        return partCategory;
    }

    public void setPartCategory(PartCategory partCategory) {
        this.partCategory = partCategory;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Part> getAll() {
        return partService.getAllWithPartCategoryAndProduct();
    }

    @Override
    public void delete() {
        partService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

    private void setFields(Part part) {
        if (part != null) {
            clearForm();
            setId(part.getId());
            partCategory = part.getPartCategory();
            product = part.getProduct();
            name = part.getName();
        }
    }

    public void update() {
        Part part = new Part();
        part.setId(getId());
        part.setPartCategory(partCategory);
        part.setProduct(product);
        part.setName(name);
        partService.update(part);
    }

    public void load() {
        if (getId() != null) {
            Part part = partService.getByIdWithPartCategoryAndProduct(getId());
            setFields(part);
        }
    }

    public void clearForm() {
        setId(null);
        partCategory = null;
        product = null;
        name = null;
    }

    public void create() {
        Part part = new Part();
        part.setPartCategory(this.partCategory);
        part.setProduct(this.product);
        part.setName(this.name);
        partService.create(part);
    }

}
