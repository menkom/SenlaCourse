package info.mastera.utils.impl;

import info.mastera.config.TestConfig;
import info.mastera.dao.IUserDao;
import info.mastera.model.enums.UserType;
import info.mastera.utils.IJwtService;
import info.mastera.utils.exceptions.AuthenticationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class JwtServiceTest {

    @Autowired
    IUserDao userDao;

    @Autowired
    IJwtService jwtService;

    @Test
    public void testNullUser() {
        String token = jwtService.createToken(null, UserType.ADMIN);
        Assert.assertEquals("", token);
    }

    public void testEmptyUserType() {
        String token = jwtService.createToken(1, null);
        Assert.assertEquals("", token);
    }

    @Test
    public void testTokenGeneration() throws AuthenticationException {
        String token = jwtService.createToken(1, UserType.RECEIVER);
        Assert.assertTrue(!StringUtils.isEmpty(token));
        jwtService.getUserGrant(token);
    }

    @Test(expected = AuthenticationException.class)
    public void testCorruptedToken() throws AuthenticationException {
        StringBuilder token = new StringBuilder(jwtService.createToken(1, UserType.ENGINEER));
        Assert.assertTrue(!StringUtils.isEmpty(token));
        token.setCharAt(1, '1');
        token.setCharAt(2, '2');

        jwtService.getUserGrant(token.toString());
    }

    @Test(expected = AuthenticationException.class)
    public void testCheckNull() throws AuthenticationException {
        jwtService.getUserGrant(null);
    }

}