package info.mastera.beans.usertype;

import info.mastera.model.enums.UserType;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class UserTypeBean {

    public List<UserType> getUserTypes() {
        return Arrays.asList(UserType.values());
    }

}
