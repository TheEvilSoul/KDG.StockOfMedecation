package live.tesnetwork.kdg.stockofmedication.entity;

import live.tesnetwork.kdg.stockofmedication.model.User;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    @Test
    public void createUserSuccessfully() {
        User user = new User("TestUser", "TestPassword");
        assertNotNull(user);
    }

    @Test
    public void getUsernameReturnsCorrectUsername() {
        User user = new User("TestUser", "TestPassword");
        assertEquals("TestUser", user.getUsername());
    }

    @Test
    public void getPasswordReturnsCorrectPassword() {
        User user = new User("TestUser", "TestPassword");
        assertEquals("TestPassword", user.getPassword());
    }

    @Test
    public void getFullNameReturnsCorrectUsername() {
        User user = new User("TestUser", "TestPassword");
        assertEquals("TestUser", user.getFullName());
    }

    @Test
    public void fromMapCreatesCorrectUser() {
        Map<String, String> map = Map.of(
                "username", "TestUser",
                "password", "TestPassword"
        );
        User user = User.fromMap(map);
        assertEquals("TestUser", user.getUsername());
        assertEquals("TestPassword", user.getPassword());
    }

    @Test
    public void toMapReturnsCorrectMap() {
        User user = new User("TestUser", "TestPassword");
        Map<String, String> map = user.toMap();
        assertEquals("TestUser", map.get("username"));
        assertEquals("TestPassword", map.get("password"));
    }
}