package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    static Connection conn = Util.getConnection();

    @Override
    public void createUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("CREATE TABLE users (ID INT AUTO_INCREMENT PRIMARY KEY , NAME VARCHAR(20), LASTNAME VARCHAR(20), AGE INT)");
        } catch (SQLException e) {

        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("User с именем – %s добавлен в базу данных%n", name);
    }

    @Override
    public void removeUserById(long id) {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE Id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            while (result.next()) {
                userList.add(new User(result.getString(2),
                                      result.getString(3),
                                      result.getByte(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
