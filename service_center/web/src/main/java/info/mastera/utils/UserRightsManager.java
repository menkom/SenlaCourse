package info.mastera.utils;

import info.mastera.model.enums.UserType;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class UserRightsManager {


    private static final String USER_TYPE_ADMIN = "admin";
    private static final String USER_TYPE_ENGINEER = "engineer";
    private static final String USER_TYPE_RECEIVER = "receiver";
    private static final String FILE_USER_RIGHTS = "userrights.properties";
    private static final String ERROR_USER_RIGHTS_FILE_NOT_FOUND = "Error. User rights file not found at address \"%s\".";
    private static final String ERROR_USER_RIGHTS_LOAD = "Error user rights load.";

    private static Map<String, Set<UserType>> userRightsMap;

    private static final Logger logger = Logger.getLogger(UserRightsManager.class);

    static {
        userRightsMap = new HashMap<String, Set<UserType>>();
        loadMapForPath();
    }

    private static void loadMapForPath() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream(FILE_USER_RIGHTS)) {
            Properties props = new Properties();
            props.load(inputStream);

            for (String key : props.stringPropertyNames()) {
                String userRights = props.getProperty(key);
                userRightsMap.put(key, getRights(userRights));
            }

        } catch (IOException e) {
            logger.error(ERROR_USER_RIGHTS_LOAD, e);
        }
    }

    private static Set<UserType> getRights(String userRights) {
        Set<UserType> result = new HashSet<UserType>();
        for (String userRight : userRights.split(",")) {
            switch (userRight) {
                case USER_TYPE_RECEIVER: {
                    result.add(UserType.RECEIVER);
                    break;
                }
                case USER_TYPE_ENGINEER: {
                    result.add(UserType.ENGINEER);
                    break;
                }
                case USER_TYPE_ADMIN: {
                    result.add(UserType.ADMIN);
                    break;
                }
            }
        }
        return result;
    }

    public static boolean checkRights(String path, UserType userType) {
        Set<UserType> userRights = userRightsMap.get(path);
        if (userRights != null) {
            return userRights.contains(userType);

        } else {
            return true;
        }
    }

}
