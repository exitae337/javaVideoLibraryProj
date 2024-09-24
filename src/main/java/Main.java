import Entities.User;
import Entities.UserRole;

import daoInterface.implementation.RoleDAOImpl;
import daoInterface.implementation.UserDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDAOImpl userDAO = new UserDAOImpl();
        RoleDAOImpl roleDAO = new RoleDAOImpl();

        // First User
        User user1 = new User();
        UserRole userRole = new UserRole();
        userRole.setId(1);
        userRole.setRoleName("admin");

        //roleDAO.addRole(userRole);

        // First User setting
        user1.setFullName("Иван Иванов");
        user1.setEmail("mail2@mail.ru");
        user1.setPassword("kokasas");
        user1.setUserRole(userRole.getId());

        // Adding USER
        //userDAO.addUser(user1);


        // Update User
        User newUser = new User();
        newUser.setId(7);
        newUser.setFullName("Иван Чепчиков");
        newUser.setEmail("mail3@mail.ru");
        newUser.setPassword("kokaksas");
        newUser.setUserRole(userRole.getId());

        //userDAO.updateUser(newUser);

        // DeleteUser
        //userDAO.deleteUserByID(6);

        // Get all users
        List<User> users = new ArrayList<>();
        users = userDAO.getAllUsers();
        System.out.println(users.toString());

        // Add Role
        //UserRole role = new UserRole();
        //role.setRoleName("not admin");
        //roleDAO.addRole(role);

        // Update role
//        UserRole role2 = new UserRole();
//        role2.setId(2);
//        role2.setRoleName("good admin");
//        roleDAO.updateRole(role2);

        // Delete Role
        //roleDAO.deleteRoleByID(2);

        //Get all roles
        System.out.println(roleDAO.getAllRoles());
    }
}
