package info.mastera;

import info.mastera.model.base.BaseObject;
import info.mastera.service.IGenericService;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

public abstract class GenericBean<T extends BaseObject> {

    private static final Logger logger = Logger.getLogger(GenericBean.class);

    private T selectedItem;

    public T getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(T selectedItem) {
        this.selectedItem = selectedItem;
    }

    public abstract void create();

    protected abstract void clearForm();

    public List<T> getAll() {
        logger.info("GenericBean.getAll; " + this.getClass());
        return getService().getAll();
    }

    protected abstract IGenericService<T> getService();

    public void delete() {
        getService().delete(selectedItem);
        if (selectedItem != null) {
            addMessage("Item with id=" + selectedItem.getId() + " deleted.");
        }
        selectedItem = null;
    }

    public void update() {
        getService().update(selectedItem);
        selectedItem = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
