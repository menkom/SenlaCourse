package info.mastera.converters;

import info.mastera.model.Customer;
import info.mastera.model.base.BaseObject;
import info.mastera.service.ICustomerService;
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
public class CustomerConverter extends GenericConverter<Customer> {

    @Inject
    private ICustomerService customerService;


    @Override
    protected Customer getById(Integer id) {
        return customerService.getById(id);
    }

}
