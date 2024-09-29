package daoInterface.implementation;

import Entities.User;
import daoInterface.UserDAO;
import daoInterface.queries.SQLUsersQueries;
import database.ConnectionProxy;
import database.ConnectorToDatabase;
import exceptions.UserDAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static volatile UserDAOImpl instance = null;
    private static final ConnectorToDatabase connectorToDatabase = ConnectorToDatabase.getInstance();

    private UserDAOImpl() {}

    @Override
    public void addUser(User user) throws UserDAOException {
        try(
                ConnectionProxy connection = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connection
                        .getRealConnection()
                        .prepareStatement(SQLUsersQueries.ADD_USER);
        ) {
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("User with this e-mail is already exists!");
            } else {
                throw new UserDAOException("Problem with adding user");
            }
        }
    }

    @Override
    public User getUserByID(int id) throws UserDAOException {
        User user = null;
        try(
                ConnectionProxy connection = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connection
                        .getRealConnection()
                        .prepareStatement(SQLUsersQueries.GET_USER)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFullName(rs.getString("user_fullname"));
                user.setEmail(rs.getString("user_email"));
                user.setPassword(rs.getString("user_password"));
                user.setUserRole(rs.getInt("user_role_id"));
            }
        } catch (SQLException e) {
            throw new UserDAOException("Problems with getting user by id");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws UserDAOException {
        List<User> userList = new ArrayList<>();
        try(
                ConnectionProxy connection = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connection
                        .getRealConnection()
                        .prepareStatement(SQLUsersQueries.GET_ALL_USERS)
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFullName(rs.getString("user_fullname"));
                user.setEmail(rs.getString("user_email"));
                user.setPassword(rs.getString("user_password"));
                user.setUserRole(rs.getInt("user_role_id"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new UserDAOException("Problems with getting all users");
        }
        return userList;
    }

    @Override
    public void updateUser(User user) throws UserDAOException {
        try(
                ConnectionProxy connection = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connection
                        .getRealConnection()
                        .prepareStatement(SQLUsersQueries.UPDATE_USER)
                ) {
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRole());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UserDAOException("Problems with updating user information");
        }
    }

    @Override
    public void deleteUserByID(int id) throws UserDAOException {
        try (
                ConnectionProxy connection = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connection
                        .getRealConnection()
                        .prepareStatement(SQLUsersQueries.DELETE_USER)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UserDAOException("Problems with deleting user");
        }
    }

    public static UserDAOImpl getInstance() {
        UserDAOImpl userLocalDAO = instance;
        if (userLocalDAO == null) {
            synchronized (UserDAOImpl.class) {
                userLocalDAO = instance;
                if (userLocalDAO == null) {
                    instance = userLocalDAO = new UserDAOImpl();
                }
            }
        }
        return instance;
    }

}
