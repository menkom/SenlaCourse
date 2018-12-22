package info.mastera.beans;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.UserLoginHistory;
import info.mastera.service.IGenericService;
import info.mastera.service.IUserLoginHistoryService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserLoginHistoryBean extends BaseListBean<UserLoginHistory> {

    @Inject
    private IUserLoginHistoryService<UserLoginHistory> userLoginHistoryService;

    @Override
    protected IGenericService<UserLoginHistory> getService() {
        return userLoginHistoryService;
    }
}
