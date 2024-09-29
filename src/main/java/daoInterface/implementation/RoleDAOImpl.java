package daoInterface.implementation;


import Entities.UserRole;
import daoInterface.RoleDAO;
import daoInterface.queries.SQLRolesQueries;
import database.ConnectionProxy;
import database.ConnectorToDatabase;
import exceptions.RoleDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {

    private static volatile RoleDAOImpl instance = null;
    private final ConnectorToDatabase connectorToDatabase = ConnectorToDatabase.getInstance();

    private RoleDAOImpl() {}

    @Override
    public void addRole(UserRole role) throws RoleDAOException {
        try(
                ConnectionProxy connection = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connection
                        .getRealConnection()
                        .prepareStatement(SQLRolesQueries.ADD_ROLE)
        ) {
            statement.setString(1, role.getRoleName());
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Role is already exists!");
            } else {
                throw new RoleDAOException("Problem with adding role");
            }
        }
    }

    @Override
    public UserRole getRoleByID(int id) throws RoleDAOException {
        UserRole role = null;
        try(
                ConnectionProxy connection = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connection
                        .getRealConnection()
                        .prepareStatement(SQLRolesQueries.GET_ROLE)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                role = new UserRole();
                role.setId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
            }
        } catch (SQLException e) {
            throw new RoleDAOException("Problems with getting role by id");
        }
        return role;
    }

    @Override
    public List<UserRole> getAllRoles() throws RoleDAOException {
        List<UserRole> roleList = new ArrayList<>();
        try(
                ConnectionProxy connection = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connection
                        .getRealConnection()
                        .prepareStatement(SQLRolesQueries.GET_ALL_ROLES)
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserRole role = new UserRole();
                role.setId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
                roleList.add(role);
            }
        } catch (SQLException e) {
            throw new RoleDAOException("Problem with getting all roles");
        }
        return roleList;
    }

    @Override
    public void updateRole(UserRole role) throws RoleDAOException {
        try(
                ConnectionProxy connection = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connection
                        .getRealConnection()
                        .prepareStatement(SQLRolesQueries.UPDATE_ROLE)
        ) {
            statement.setString(1, role.getRoleName());
            statement.setInt(2, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RoleDAOException("Problems with deleting user");
        }
    }

    @Override
    public void deleteRoleByID(int id) throws RoleDAOException {
        try(
                ConnectionProxy connection = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connection
                        .getRealConnection()
                        .prepareStatement(SQLRolesQueries.DELETE_ROLE)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RoleDAOException("Problems with deleting role");
        }
    }

    public static RoleDAOImpl getInstance() {
        RoleDAOImpl roleLocalDAO = instance;
        if(roleLocalDAO == null) {
            synchronized (RoleDAOImpl.class) {
                roleLocalDAO = instance;
                if(roleLocalDAO == null) {
                    instance = roleLocalDAO = new RoleDAOImpl();
                }
            }
        }
        return instance;
    }

}
