package info.mastera.beans;

import info.mastera.model.User;
import info.mastera.service.IGenericService;
import info.mastera.service.IUserService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Scope("session")
public class UserListBean extends BaseListBean<User> {

    @Inject
    private IUserService<User> userService;

    @Override
    protected IGenericService<User> getService() {
        return userService;
    }
}
