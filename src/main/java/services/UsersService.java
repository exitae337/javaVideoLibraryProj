package services;

import Entities.User;
import daoInterface.implementation.UserDAOImpl;
import exceptions.ConnectionToDatabaseException;
import exceptions.UserDAOException;

import java.util.List;

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
