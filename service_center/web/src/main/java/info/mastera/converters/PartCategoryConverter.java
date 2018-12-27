package info.mastera.converters;

import info.mastera.model.Customer;
import info.mastera.model.PartCategory;
import info.mastera.model.base.BaseObject;
import info.mastera.service.IPartCategoryService;
import org.springframework.util.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PartCategoryConverter extends GenericConverter<PartCategory> {

    @Inject
    private IPartCategoryService partCategoryService;

    @Override
    protected PartCategory getById(Integer id) {
        return partCategoryService.getById(id);
    }

}
