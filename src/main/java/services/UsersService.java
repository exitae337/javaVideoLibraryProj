package services;

import Entities.User;
import daoInterface.implementation.UserDAOImpl;
import exceptions.ConnectionToDatabaseException;
import exceptions.UserDAOException;

import java.util.List;
import java.util.Objects;

public class UsersService {

    private static volatile UsersService instance = null;
    private static final UserDAOImpl userDAO = UserDAOImpl.getInstance();

    private UsersService() {}

    public List<User> getAllUsers() throws UserDAOException {
        return userDAO.getAllUsers();
    }
    public User getUserById(int id) throws UserDAOException {
        return userDAO.getUserByID(id);
    }

    public boolean isRegistered(String email) throws UserDAOException {
        return userDAO.getUserByEmail(email) != null;
    }

    public boolean isLogin(String email, String password) throws UserDAOException {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            return Objects.equals(user.getPassword(), password);
        } else {
            return false;
        }
    }

    public void registerUser(User user) throws UserDAOException {
        userDAO.addUser(user);
    }

    public static UsersService getInstance() {
        UsersService localServiceInstance = instance;
        if (localServiceInstance == null) {
            synchronized (UsersService.class) {
                localServiceInstance = instance;
                if(localServiceInstance == null) {
                    localServiceInstance = new UsersService();
                    instance = localServiceInstance;
                }
            }
        }
        return instance;
    }
}
