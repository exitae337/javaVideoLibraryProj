package daoInterface.dao;

import Entities.UserRole;
import exceptions.RoleDAOException;
import exceptions.UserDAOException;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {
    void addRole(UserRole role) throws RoleDAOException;
    UserRole getRoleByID(int id) throws RoleDAOException;
    List<UserRole> getAllRoles() throws RoleDAOException;
    void updateRole(UserRole role) throws RoleDAOException;
    void deleteRoleByID(int id) throws RoleDAOException;

}
