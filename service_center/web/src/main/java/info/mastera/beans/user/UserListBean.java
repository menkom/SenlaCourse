package info.mastera.beans.user;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.User;
import info.mastera.service.IUserService;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@SessionScoped
public class UserListBean extends BaseListBean<User> {

    @Inject
    private IUserService userService;

    @Override
    public List<User> getAll() {
        //TODO Use Lazy method instead
        return userService.getAll();
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
