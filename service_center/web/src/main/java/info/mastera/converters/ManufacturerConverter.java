package info.mastera.converters;

import info.mastera.model.Manufacturer;
import info.mastera.service.IManufacturerService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ManufacturerConverter extends GenericConverter<Manufacturer> {

    @Inject
    private IManufacturerService manufacturerService;

    @Override
    protected Manufacturer getById(Integer id) {
        return manufacturerService.getById(id);
    }

}
