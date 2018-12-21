package info.mastera.model;

import info.mastera.model.enums.UserType;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void equals() {
        User[] users = new User[2];

        for (User user : users) {
            user = new User();
            user.setId(2);
            user.setUsername("user");
            user.setPassword("pass");
            user.setUserType(UserType.ENGINEER);
        }
        Assert.assertEquals(users[0], users[1]);

    }
}