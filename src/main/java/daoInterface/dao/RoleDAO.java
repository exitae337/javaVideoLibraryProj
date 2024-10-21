package daoInterface.dao;

import entity.UserRole;
import exception.RoleDAOException;

import java.util.List;

public interface RoleDAO {
    void addRole(UserRole role) throws RoleDAOException;
    UserRole getRoleByID(int id) throws RoleDAOException;
    List<UserRole> getAllRoles() throws RoleDAOException;
    void updateRole(UserRole role) throws RoleDAOException;
    void deleteRoleByID(int id) throws RoleDAOException;

}
