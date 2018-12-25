package info.mastera.beans.manufacturer;

import info.mastera.beans.base.BaseBean;
import info.mastera.model.Manufacturer;
import info.mastera.service.IManufacturerService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ManufacturerBean extends BaseBean {

    @Inject
    private IManufacturerService manufacturerService;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setFields(Manufacturer manufacturer) {
        if (manufacturer != null) {
            clearForm();
            setId(manufacturer.getId());
            name = manufacturer.getName();
        }
    }

    public void clearForm() {
        setId(null);
        name = null;
    }

    public void create() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(this.name);
        manufacturerService.create(manufacturer);
    }

    public void update() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(getId());
        manufacturer.setName(name);
        manufacturerService.update(manufacturer);
    }

    public void load() {
        if (getId() != null) {
            Manufacturer manufacturer = manufacturerService.getById(getId());
            setFields(manufacturer);
        }
    }

}
