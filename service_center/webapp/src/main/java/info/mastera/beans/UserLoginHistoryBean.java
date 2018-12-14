package info.mastera.beans;

import info.mastera.model.UserLoginHistory;
import info.mastera.service.IGenericService;
import info.mastera.service.IUserLoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserLoginHistoryBean extends BaseListBean<UserLoginHistory> {

    @Inject
    private IUserLoginHistoryService<UserLoginHistory> userLoginHistoryService;

    @Override
    protected IGenericService<UserLoginHistory> getService() {
        return userLoginHistoryService;
    }
}
