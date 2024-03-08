package live.tesnetwork.kdg.stockofmedication.controller;


import live.tesnetwork.kdg.stockofmedication.entity.MedicationCategory;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.utils.Config;
import live.tesnetwork.kdg.stockofmedication.utils.EncryptionHelper;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.Nullable;

import java.sql.*;

public class MySQLDatabaseController {

    private Connection connection;

    @Language("MySQL")
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM Users WHERE username = ?";

    @Language("MySQL")
    private static final String INSERT_USER = "INSERT INTO Users(username, passwordHash) VALUES(?, ?)";

    @Language("MySQL")
    private static final String DELETE_USER_BY_USERNAME = "DELETE FROM Users WHERE username = ?";

    @Language("MySQL")
    private static final String INSERT_MEDICATION_CATEGORY = "INSERT MedicationCategory(medicationCategoryName) VALUES(?)";


    public MySQLDatabaseController(Config config) {
        try {
            String url = "jdbc:mysql://%s:%s/%s?useSSL=%s".formatted(
                    config.get("DB_IP"),
                    config.get("DB_PORT"),
                    config.get("DB_NAME"),
                    config.get("DB_USE_SSL")
            );
            connection = DriverManager.getConnection(url, config.get("DB_USER"), config.get("DB_PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public User getUserByUsername(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getString("username"),
                            resultSet.getString("passwordHash")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createUser(String name, String password, boolean isAHash) {
        if (!isAHash) password = EncryptionHelper.hashPassword(password);
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUserByUsername(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createMedicationCategory(String medicationCategoryName) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEDICATION_CATEGORY)) {
            preparedStatement.setString(1, medicationCategoryName);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public MedicationCategory getMedicationCategoryByName(String medicationCategoryName) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, medicationCategoryName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    //return MedicationCategory.add(
                    //        resultSet.getInt("medicationCategoryId"),
                    //        resultSet.getString("medicationCategoryName")
                    //);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}