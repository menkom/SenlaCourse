package info.mastera.beans.userlognhistory;

import info.mastera.beans.base.BaseListBean;
import info.mastera.model.UserLoginHistory;
import info.mastera.service.IUserLoginHistoryService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UserLoginHistoryListBean extends BaseListBean<UserLoginHistory> {

    @Inject
    private IUserLoginHistoryService userLoginHistoryService;

    @Override
    public List<UserLoginHistory> getAll() {
        //TODO Use Lazy method instead
        return userLoginHistoryService.getAll();
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
