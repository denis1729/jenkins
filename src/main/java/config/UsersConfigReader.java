package config;

import entities.User;
import org.apache.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class UsersConfigReader {
    private Logger log = Logger.getLogger(getClass());
    private static final String USER_EMAIL = "user email";
    private static final String USER_PASSWORD = "user password";
    private static final String FIRST_NAME = "first name";
    private static final String LAST_NAME = "last name";

    private static HashMap<String, User> users;

    private static UsersConfigReader instance;

    /**
     * Gets instance of UsersConfigReader.
     *
     * @return the current instance.
     */
    public static UsersConfigReader getInstance() {
        if (instance == null) {
            instance = new UsersConfigReader();
        }
        return instance;
    }

    /**
     * Reads values from json file.
     *
     * @param UsersConfigFileName - User file of configuration.
     */
    public void initialize(final String UsersConfigFileName) {
        log.info("UsersConfigReader initialize: Read the users settings from " + UsersConfigFileName);

        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(UsersConfigFileName));
        } catch (IOException except) {
            except.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Map<String, Map<String, String>> dataUsers = (Map<String, Map<String, String>>) obj;

        users = new HashMap<>();
        dataUsers.forEach((alias, data) -> {
            User user = new User();
            user.setUserEmail(data.get(USER_EMAIL));
            user.setPassword(data.get(USER_PASSWORD));
            user.setFirstName(data.get(FIRST_NAME));
            user.setLastName(data.get(LAST_NAME));
            user.setAlias(alias);
            users.put(alias, user);
        });
    }

    /**
     * Gets the user given the alias.
     *
     * @param userAlias - The alias of the user.
     * @return User.
     */
    public User getUserByAlias(String userAlias) {
        return users.get(userAlias);
    }
}
