package daoInterface;

import Entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void addUser(User user) throws SQLException;
    User getUserByID(int id) throws SQLException;
    List<User> getAllUsers() throws SQLException, InterruptedException;
    void updateUser(User user) throws SQLException;
    void deleteUserByID(int id) throws SQLException;
}
