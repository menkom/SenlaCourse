package info.mastera.converters;

import info.mastera.model.Customer;
import info.mastera.model.base.BaseObject;
import info.mastera.service.ICustomerService;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Scope("session")
//@FacesConverter(value = "customerConverter")
public class CustomerConverter implements Converter {

    private static final String NO_OBJECT_CUSTOMER_FOUND = "No object Customer found for value: ";
    private static final String ERROR_NOT_A_VALID_ID = "%s is not a valid id";

    @Inject
    private ICustomerService<Customer> customerService;

    @Override
    public Customer getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isEmpty(value) || StringUtils.isEmpty(value.trim())) {
            return null;
        }
        Integer id = Integer.valueOf(value);
        Customer customer = customerService.getById(id);
        if (customer == null) {
            throw new ConverterException(NO_OBJECT_CUSTOMER_FOUND + id);
        }

        return customer;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof BaseObject) {
            Integer id = ((BaseObject) value).getId();
            return (id != null) ? id.toString() : null;
        } else {
            throw new ConverterException(new FacesMessage(String.format(ERROR_NOT_A_VALID_ID, value)));
        }
    }
}
