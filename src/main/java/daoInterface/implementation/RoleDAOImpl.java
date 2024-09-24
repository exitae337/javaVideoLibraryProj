package daoInterface.implementation;

import Entities.User;
import Entities.UserRole;
import daoInterface.RoleDAO;
import database.ConnectorToDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {

    private ConnectorToDatabase connectorToDatabase;

    public RoleDAOImpl() {}

    @Override
    public void addRole(UserRole role) throws SQLException {
        String sqlRequest = "INSERT INTO user_role (role_name) VALUES (?)";
        try {
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
            statement.setString(1, role.getRoleName());
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Role is already exists!");
            } else {
                System.out.println("Error in adding role!");
            }
        } finally {
            connectorToDatabase.closeConnection();
        }

    }

    @Override
    public UserRole getRoleByID(int id) throws SQLException {
        String sqlRequest = "SELECT * FROM user_role WHERE role_id = ?";
        UserRole role = null;

        try {
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                role = new UserRole();
                role.setId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error in getting role!");
            e.printStackTrace();
        } finally {
            connectorToDatabase.closeConnection();
        }
        return role;
    }

    @Override
    public List<UserRole> getAllRoles() throws SQLException {
        String sqlRequest = "SELECT * FROM user_role";
        List<UserRole> roleList = new ArrayList<>();
        try {
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                UserRole role = new UserRole();
                role.setId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
                roleList.add(role);
            }
        } catch (SQLException e) {
            System.out.println("Error in getting all roles!");
        } finally {
            connectorToDatabase.closeConnection();
        }
        return roleList;
    }

    @Override
    public void updateRole(UserRole role) throws SQLException {
        String sqlRequest = "UPDATE user_role SET role_name = ? WHERE role_id = ?";
        try {
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
            statement.setString(1, role.getRoleName());
            statement.setInt(2, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in updating role!");
            e.printStackTrace();
        } finally {
            connectorToDatabase.closeConnection();
        }
    }

    @Override
    public void deleteRoleByID(int id) throws SQLException {
        String sqlRequest = "DELETE FROM user_role WHERE role_id = ?";
        try {
            connectorToDatabase = ConnectorToDatabase.getInstance();
            PreparedStatement statement = connectorToDatabase.getConnection().prepareStatement(sqlRequest);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in deleting role");
            e.printStackTrace();
        } finally {
            connectorToDatabase.closeConnection();
        }
    }
}
