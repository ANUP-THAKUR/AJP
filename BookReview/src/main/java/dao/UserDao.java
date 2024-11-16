package dao;

import entities.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    List<User> getAllUsers();
    void deleteUser(int userId);
}
