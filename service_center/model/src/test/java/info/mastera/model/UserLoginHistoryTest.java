package info.mastera.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UserLoginHistoryTest {

    @Test
    public void equals() {

        UserLoginHistory[] userLoginHistories = new UserLoginHistory[2];

        for (UserLoginHistory userLoginHistory : userLoginHistories) {
            userLoginHistory = new UserLoginHistory();
            userLoginHistory.setId(2);
            userLoginHistory.setLoginTime(new Date());
        }
        Assert.assertEquals(userLoginHistories[0], userLoginHistories[1]);


    }
}