package info.mastera.beans.product;

import info.mastera.beans.base.BaseBean;
import info.mastera.service.IManufacturerService;
import info.mastera.service.IProductService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductBean extends BaseBean {
    private static final String URL_PAGE = "/views/product/productPageList.xhtml";

    @Inject
    private IProductService productService;

    public String cancel() {
        clearForm();
        return URL_PAGE;
    }

    private void clearForm() {
    }

    public String create() {
//        manufacturerService.create(manu);
        clearForm();
        return URL_PAGE;
    }

}
