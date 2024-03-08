package live.tesnetwork.kdg.stockofmedication.entity;

import live.tesnetwork.kdg.stockofmedication.utils.Convertable;

import java.util.Map;

public class User implements Convertable {
    private final String username;
    private final String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User fromMap(Map<String, String> map) {
        return new User(
                map.get("username"),
                map.get("password")
        );
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getFullName() {
        return getUsername();
    }

    @Override
    public Map<String, String> toMap() {
        return Map.of(
                "username", username,
                "password", password
        );
    }
}
