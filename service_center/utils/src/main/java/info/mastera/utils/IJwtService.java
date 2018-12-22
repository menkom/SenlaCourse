package info.mastera.utils;

import info.mastera.model.enums.UserType;
import info.mastera.utils.exceptions.AuthenticationException;

public interface IJwtService {
    String createToken(Integer userId, UserType userType);

    UserType getUserGrant(String token) throws AuthenticationException;
}
