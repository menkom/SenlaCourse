package info.mastera.beans.user;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.User;
import info.mastera.service.IUserService;
import org.apache.log4j.Logger;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class UserListBean extends BaseListBean<User> {

    private static final Logger logger = Logger.getLogger(UserListBean.class);

    @Inject
    private IUserService userService;

    @Override
    public List<User> getAll() {
        return userService.getAllAndCustomer();
    }

    @Override
    public void delete() {
        logger.info("user list delete");
        userService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

}
