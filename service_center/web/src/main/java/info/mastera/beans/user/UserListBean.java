package info.mastera.beans.user;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.User;
import info.mastera.service.IGenericService;
import info.mastera.service.IUserService;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UserListBean extends BaseListBean<User> {

    @Inject
    private IUserService<User> userService;

    @Override
    protected IGenericService<User> getService() {
        return userService;
    }
}
