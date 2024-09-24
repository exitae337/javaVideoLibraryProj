package daoInterface.implementation;

import Entities.User;
import Entities.UserRole;
import daoInterface.UserDAO;
import database.ConnectorToDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private ConnectorToDatabase connectorToDatabase;

    public UserDAOImpl() {}

    @Override
    public void addUser(User user) throws SQLException {

        String sqlRequest = "INSERT INTO users (user_fullname, user_email, user_password, user_role_id) VALUES (?, ?, ?, ?)";

        try{
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("User with this e-mail is already exists!");
            } else {
                System.out.println("Problem with adding user");
                e.printStackTrace();
            }
        } finally {
            connectorToDatabase.closeConnection();
        }
    }

    @Override
    public User getUserByID(int id) throws SQLException {
        String sqlRequest = "SELECT * FROM users WHERE user_id = ?";
        User user = null;

        try {
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
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
            e.printStackTrace();
            System.out.println("Problem in getting user by id!");
        } finally {
            connectorToDatabase.closeConnection();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        String sqlRequest = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try {
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
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
            System.out.println("Problem in getting all users from db!");
            e.printStackTrace();
        } finally {
            connectorToDatabase.closeConnection();
        }
        return userList;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sqlRequest = "UPDATE users SET user_fullname = ?, user_email = ?, user_password = ?, user_role_id = ? WHERE user_id = ?";
        try {
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRole());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in user-updating!");
            e.printStackTrace();
        } finally {
            connectorToDatabase.closeConnection();
        }
    }

    @Override
    public void deleteUserByID(int id) throws SQLException {
        String sqlRequest = "DELETE FROM users WHERE user_id = ?";
        try {
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem in deleting user!");
            e.printStackTrace();
        } finally {
            connectorToDatabase.closeConnection();
        }
    }


}
