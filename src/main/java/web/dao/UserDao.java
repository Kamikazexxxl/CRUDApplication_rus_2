package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void addUser(String name, String surname, int age);
    void deleteUser(int id);
    User getUser(int id);
    void editUser(int id, String name, String surname, int age);
}