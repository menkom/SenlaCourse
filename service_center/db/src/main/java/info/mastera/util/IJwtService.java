package info.mastera.util;

import info.mastera.model.enums.UserType;
import info.mastera.util.exceptions.AuthenticationException;

public interface IJwtService {
    String createToken(Integer userId, UserType userType);

    UserType getUserGrant(String token) throws AuthenticationException;
}
