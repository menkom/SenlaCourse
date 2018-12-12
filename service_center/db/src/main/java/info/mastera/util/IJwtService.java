package info.mastera.util;

import info.mastera.model.enums.UserType;
import info.mastera.util.exceptions.AuthenticationException;

public interface IJwtService {
    String createToken(String username, String password);

    UserType getUserGrant(String token) throws AuthenticationException;
}
