package info.mastera.converters;

import info.mastera.model.Product;
import info.mastera.model.base.BaseObject;
import org.springframework.util.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public abstract class GenericConverter<T extends BaseObject> implements Converter {


    private static final String NO_OBJECT_FOUND = "No object found for value: ";
    private static final String ERROR_NOT_A_VALID_ID = " is not a valid id";


    protected abstract T getById(Integer id);

    @Override
    public T getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isEmpty(value) || StringUtils.isEmpty(value.trim())) {
            return null;
        }
        Integer id = Integer.valueOf(value);
        T item = getById(id);
        if (item == null) {
            throw new ConverterException(NO_OBJECT_FOUND + id);
        }
        return item;
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
            throw new ConverterException(new FacesMessage(value + ERROR_NOT_A_VALID_ID));
        }
    }
}
