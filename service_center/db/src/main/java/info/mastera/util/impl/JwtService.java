package info.mastera.util.impl;

import info.mastera.model.enums.UserType;
import info.mastera.util.IJwtService;
import info.mastera.util.exceptions.AuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.Date;

/**
 * Service to work with JJWT (Java JWT: JSON Web Token for Java)
 */
@Component
public class JwtService implements IJwtService {

    private static final Logger logger = Logger.getLogger(JwtService.class);

    private static final String TOKEN_INVALID = "Invalid token";
    private static final String TOKEN_CORRUPTED = "Token corrupted";
    private static final String TOKEN_IS_NOT_EXPIRED = "Token is not expired.";
    private static final String TOKEN_IS_EXPIRED = "Token is expired.";
    private static final String TOKEN_IS_NULL = "Token is null";

    private static final String CLAIM_USERID = "uid";
    private static final String CLAIM_USERTYPE = "utype";

    private static final SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
    private static final String issuer = "info.mastera";
    private static final TemporalAmount TOKEN_VALIDITY = Duration.ofMinutes(30L);
    private static String encodedKey;

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    static {
        generateEncodedKey();
    }

    /**
     * Method to generate end Date of token actuality
     * according to instant time and time of token life length (TOKEN_VALIDITY)
     *
     * @param date calculates period from this date
     * @return Date of expire
     */
    private static Date getExpirationPeriod(Date date) {
        return Date.from(date.toInstant().plus(TOKEN_VALIDITY));
    }

    /**
     * Once per Application life generated encodedKey for secure singing Tokens
     */
    private static void generateEncodedKey() {
        byte[] sharedKey = new byte[32];
        new SecureRandom().nextBytes(sharedKey);
        encodedKey = TextCodec.BASE64.encode(sharedKey);
        logger.info("encodedKey generated:" + encodedKey);
    }

    /**
     * Generates token for granted user and password
     *
     * @param userId - userId just in case. there is no real use for this param.
     *                 just additional param for security
     * @param userType - usertype for different program levels
     * @return String active token for current user
     */
    @Override
    public String createToken(Integer userId, UserType userType) {
        if (userId == null || userType == null)
            return "";
        Date now = new Date();
        logger.info("Token is issued to \"" +
                userId + "\" from " + formatter.format(now) +
                " to " + formatter.format(getExpirationPeriod(now)));
        return Jwts.builder()
                .setIssuer(issuer)
                .setExpiration(getExpirationPeriod(now))
                .setIssuedAt(now)
                .claim(CLAIM_USERID, userId)
                .claim(CLAIM_USERTYPE, userType)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(algorithm, encodedKey)
                .compact();
    }

    /**
     * Give a UserType according to token
     *
     * @param token JSON Web Token
     * @return UserType as result we receive usertype of enum UserType
     * @throws AuthenticationException Throws exceptions in case of token errors
     */
    @Override
    public UserType getUserGrant(String token) throws AuthenticationException {
        if (token == null) {
            throw new AuthenticationException(TOKEN_IS_NULL);
        }
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(encodedKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException ex) {
            logger.error(ex);
            throw new AuthenticationException(TOKEN_CORRUPTED);
        }

        logger.info("Claims:" + claims);

        Date expiredAt = claims.getExpiration();
        Date issuedAt = claims.getIssuedAt();

        if (isTokenDatesFaulty(issuedAt, expiredAt)) {
            logger.error(TOKEN_INVALID);
            throw new AuthenticationException(TOKEN_INVALID);
        }

        if (expiredAt.after(new Date())) {
            logger.info(TOKEN_IS_NOT_EXPIRED);
            return isFullAuthenticated(claims);
        } else {
            throw new AuthenticationException(TOKEN_IS_EXPIRED);
        }
    }

    private UserType isFullAuthenticated(Claims claims) {
        UserType userType = UserType.valueOf(claims.get(CLAIM_USERTYPE, String.class));
        logger.info("Access is granted as \"" + userType + "\"");
        logger.info("Token is actual from " + formatter.format(claims.get(Claims.ISSUED_AT, Date.class)) +
                " to " + formatter.format(claims.get(Claims.EXPIRATION, Date.class)));
        return userType;
    }

    /**
     * Check all possible elementary combination in case of date manipulations
     *
     * @param issuedAt  Issued Date extracted from claims
     * @param expiredAt Expired Date extracted from claims
     * @return returns have then been changed or they are correct
     */
    private boolean isTokenDatesFaulty(Date issuedAt, Date expiredAt) {
        return (expiredAt == null) ||
                (issuedAt == null) ||
                expiredAt.before(issuedAt) ||
                !getExpirationPeriod(issuedAt).equals(expiredAt);
    }

}