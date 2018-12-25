package info.mastera.beans.product;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Product;
import info.mastera.service.ICustomerService;
import info.mastera.service.IManufacturerService;
import info.mastera.service.IProductService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ProductListBean  extends BaseListBean<Product> {

    @Inject
    private IProductService productService;

    @Override
    public List<Product> getAll() {
        return productService.getAll();
    }

    @Override
    public void delete() {
        productService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

}
