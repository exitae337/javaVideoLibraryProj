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
            statement.setInt(4, user.getRole().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("User with this e-mail is already exists!");
            } else {
                System.out.println("Problem with adding user");
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
                user.setUserRole(getRoleByID(rs.getInt("user_role_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem in getting user by id!");
        } finally {
            connectorToDatabase.closeConnection();
        }
        return user;
    }

    private UserRole getRoleByID(int userRoleId) throws SQLException {
        String sqlRequest = "SELECT * FROM user_role WHERE role_id = ?";
        UserRole role = null;

        try {
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
            statement.setInt(1, userRoleId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                role = new UserRole();
                role.setId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error in getting role id");
            e.printStackTrace();
        } finally {
            connectorToDatabase.closeConnection();
        }
        return role;
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
                user.setUserRole(getRoleByID(rs.getInt("user_role_id")));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Problem in getting all users from db!");
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
            statement.setInt(4, user.getRole().getId());
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
