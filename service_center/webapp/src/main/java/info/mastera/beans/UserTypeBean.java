package info.mastera.beans;

import info.mastera.model.enums.UserType;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
public class UserTypeBean {

    public List<UserType> getUserTypes() {
        return Arrays.asList(UserType.values());
    }

}
