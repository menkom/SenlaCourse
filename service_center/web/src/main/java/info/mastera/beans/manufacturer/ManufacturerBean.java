package info.mastera.beans.manufacturer;

import info.mastera.beans.base.BaseBean;
import info.mastera.model.Manufacturer;
import info.mastera.service.IManufacturerService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ManufacturerBean extends BaseBean {

    private static final String URL_PAGE = "/views/manufacturer/manufacturerPageList.xhtml";

    @Inject
    private IManufacturerService manufacturerService;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String cancel() {
        clearForm();
        return URL_PAGE;
    }

    private void clearForm() {
        name = null;
    }

    public String create() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(this.name);
        manufacturerService.create(manufacturer);
        clearForm();
        return URL_PAGE;
    }

}
