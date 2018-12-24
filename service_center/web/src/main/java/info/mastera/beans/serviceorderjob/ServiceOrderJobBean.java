package info.mastera.beans.serviceorderjob;

import info.mastera.beans.base.BaseBean;
import info.mastera.service.IManufacturerService;
import info.mastera.service.IServiceOrderService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ServiceOrderJobBean extends BaseBean {

    private static final String URL_PAGE = "/views/customer/customerPageList.xhtml";

    @Inject
    private IServiceOrderService serviceOrderService;

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
