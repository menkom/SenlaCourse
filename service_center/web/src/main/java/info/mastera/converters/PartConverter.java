package info.mastera.converters;

import info.mastera.model.Manufacturer;
import info.mastera.model.Part;
import info.mastera.service.IManufacturerService;
import info.mastera.service.IPartService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PartConverter extends GenericConverter<Part> {

    @Inject
    private IPartService partService;

    @Override
    protected Part getById(Integer id) {
        return partService.getById(id);
    }

}
