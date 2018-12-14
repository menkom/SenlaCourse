package info.mastera.beans;

import info.mastera.model.base.BaseObject;
import info.mastera.service.IGenericService;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.List;

@Named
public abstract class BaseListBean<T extends BaseObject> extends BaseBean {

    private static final Logger logger = Logger.getLogger(BaseListBean.class);

    private T selectedItem;

    public T getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(T selectedItem) {
        this.selectedItem = selectedItem;
        logger.info("#BaseListBean_SetSelectedItem:" + selectedItem);
    }

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

    public void clearSelected() {
        logger.info("GenericBean.clearSelected()");
        selectedItem = null;
    }

}
