package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Fail");
            throw new RuntimeException(e);
        }

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Vasia", "Pupkin", (byte) 12);
        userDaoJDBC.saveUser("Alisa", "Ivanova", (byte) 20);
        userDaoJDBC.saveUser("Samson", "Vostraykov", (byte) 35);
        userDaoJDBC.saveUser("Anton", "Nedosekin", (byte) 12);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
