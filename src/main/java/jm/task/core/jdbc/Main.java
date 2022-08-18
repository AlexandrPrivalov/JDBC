package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Vasia", "Pupkin", (byte) 12);
        userService.saveUser("Alisa", "Ivanova", (byte) 20);
        userService.saveUser("Samson", "Vostraykov", (byte) 35);
        userService.saveUser("Anton", "Nedosekin", (byte) 12);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
