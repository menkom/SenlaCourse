package info.mastera.util.impl;

import info.mastera.config.TestDataBaseConfig;
import info.mastera.dao.IUserDao;
import info.mastera.util.IJwtService;
import info.mastera.util.exceptions.AuthenticationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@Transactional
public class JwtServiceTest {

    @Autowired
    IUserDao userDao;

    @Autowired
    IJwtService jwtService;

    @Test
    public void testEmptyUser() {
        String token = jwtService.createToken("", "AnyPass");
        Assert.assertEquals("", token);
    }

    public void testEmptyPassword() {
        String token = jwtService.createToken("AnyName", "");
        Assert.assertEquals("", token);
    }

    @Test
    public void testWrongUser() {
        String token = jwtService.createToken("wrongUser", "somePass");
        Assert.assertEquals("", token);
    }

    @Test
    public void testWrongPassword() {
        String token = jwtService.createToken("user", "somePass");
        Assert.assertEquals("", token);
    }

    @Test
    public void testTokenGeneration() throws AuthenticationException {
        String token = jwtService.createToken("user", "7pas");
        Assert.assertNotNull(token);
        Assert.assertTrue(!token.isEmpty());
        Assert.assertTrue(token.length() > 0);
        jwtService.getUserGrant(token);
    }

    @Test(expected = AuthenticationException.class)
    public void testCorruptedToken() throws AuthenticationException {
        StringBuilder token = new StringBuilder(jwtService.createToken("user", "7pas"));
        Assert.assertNotNull(token);
        token.setCharAt(1, '1');
        token.setCharAt(2, '2');

        jwtService.getUserGrant(token.toString());
    }

    @Test(expected = AuthenticationException.class)
    public void testCheckNull() throws AuthenticationException {
        jwtService.getUserGrant(null);
    }

}