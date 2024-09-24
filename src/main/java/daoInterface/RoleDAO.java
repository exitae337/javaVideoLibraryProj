package daoInterface;

import Entities.UserRole;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {
    void addRole(UserRole role) throws SQLException;
    UserRole getRoleByID(int id) throws SQLException;
    List<UserRole> getAllRoles() throws SQLException;
    void updateRole(UserRole role) throws SQLException;
    void deleteRoleByID(int id) throws SQLException;

}
