package info.mastera.beans.userloginhistory;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.UserLoginHistory;
import info.mastera.service.IUserLoginHistoryService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class UserLoginHistoryListBean extends BaseListBean<UserLoginHistory> {

    @Inject
    private IUserLoginHistoryService userLoginHistoryService;

    @Override
    public List<UserLoginHistory> getAll() {
        return userLoginHistoryService.getAllAndUser();
    }

    @Override
    public void delete() {
        userLoginHistoryService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

    @Override
    public void update() {
        userLoginHistoryService.update(getSelectedItem());
    }

}
