package info.mastera.beans;

import info.mastera.beans.base.BaseBean;
import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Manufacturer;
import info.mastera.model.Product;
import info.mastera.service.IManufacturerService;
import info.mastera.service.IProductService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ProductBean extends BaseListBean<Product> {

    @Inject
    private IProductService productService;

    private String code;
    private Manufacturer manufacturer;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Product> getAll() {
        return productService.getAllWithManufacturer();
    }

    @Override
    public void delete() {
        productService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

    private void setFields(Product product) {
        if (product != null) {
            clearForm();
            setId(product.getId());
            code = product.getCode();
            name = product.getName();
            manufacturer = product.getManufacturer();
        }
    }

    public void update() {
        Product product = new Product();
        product.setId(getId());
        product.setCode(code);
        product.setName(name);
        product.setManufacturer(manufacturer);
        productService.update(product);
    }

    public void load() {
        if (getId() != null) {
            Product product = productService.getByIdWithManufacturer(getId());
            setFields(product);
        }
    }

    public void clearForm() {
        setId(null);
        code = null;
        name = null;
        manufacturer = null;
    }

    public void create() {
        Product product = new Product();
        product.setCode(this.code);
        product.setName(this.name);
        product.setManufacturer(this.manufacturer);
        productService.create(product);
    }

}
