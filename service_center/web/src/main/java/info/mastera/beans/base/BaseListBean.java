package info.mastera.beans.base;

import info.mastera.model.base.BaseObject;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.List;

@Named
public abstract class BaseListBean<T extends BaseObject> extends BaseBean {

    private static final Logger logger = Logger.getLogger(BaseListBean.class);

    private T selectedItem;

    protected static final String MESSAGE_ITEM_DELETED = "Item with id=%s deleted.";

    public T getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(T selectedItem) {
        this.selectedItem = selectedItem;
    }

    public abstract List<T> getAll();

    public abstract void delete();

    public void clearSelected() {
        logger.info("BaseListBean_clearSelected()");
        selectedItem = null;
    }

}
