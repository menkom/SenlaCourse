package info.mastera.beans.user;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.User;
import info.mastera.service.IUserService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class UserListBean extends BaseListBean<User> {

    @Inject
    private IUserService userService;

    @Override
    public List<User> getAll() {
        return userService.getAllAndCustomer();
    }

    @Override
    public void delete() {
        userService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

    @Override
    public void update() {
        userService.update(getSelectedItem());
    }

}
