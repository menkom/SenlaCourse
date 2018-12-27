package info.mastera.beans;

import info.mastera.beans.base.BaseBean;
import info.mastera.utils.UserRightsManager;

import javax.inject.Named;

@Named
public class MenuBean extends BaseBean {

    public boolean showComponent(String path) {
        return UserRightsManager.checkRights(path, this.getAuthentication());
    }

}
