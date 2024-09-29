// FOR TESTING

import Entities.User;
import Entities.UserRole;
import daoInterface.implementation.RoleDAOImpl;
import daoInterface.implementation.UserDAOImpl;
import exceptions.RoleDAOException;
import exceptions.UserDAOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException, UserDAOException, RoleDAOException {
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        RoleDAOImpl roleDAO = RoleDAOImpl.getInstance();

        ExecutorService es = Executors.newFixedThreadPool(24);
        for (int i = 0; i < 24; i++) {
            es.submit(new Work(userDAO));
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);

        User user1 = new User();
        user1.setFullName("Сасис Корочкин");
        user1.setPassword("20022001");
        user1.setEmail("red1s2@mail.ru");
        user1.setUserRole(1);
        userDAO.addUser(user1);

        UserRole role = new UserRole();
        role.setRoleName("posps");
        roleDAO.addRole(role);

        System.out.println("Main " + userDAO.getAllUsers());
    }
}

class Work implements Runnable {
    private final UserDAOImpl userDAO;

    Work(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(userDAO.getAllUsers());
        } catch (UserDAOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}