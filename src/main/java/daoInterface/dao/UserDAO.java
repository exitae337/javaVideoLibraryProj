package daoInterface.dao;

import entity.User;
import exception.UserDAOException;

import java.util.List;

public interface UserDAO {
    void addUser(User user) throws UserDAOException;
    User getUserByID(int id) throws UserDAOException;
    List<User> getAllUsers() throws UserDAOException;
    void updateUser(User user) throws UserDAOException;
    void deleteUserByID(int id) throws UserDAOException;
    User getUserByEmail(String email) throws UserDAOException;
}
