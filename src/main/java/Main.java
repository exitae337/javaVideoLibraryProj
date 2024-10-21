import daoInterface.implementation.UserDAOImpl;
import entity.User;
import exception.UserDAOException;
import service.UsersService;


// FOR TESTING
public class Main {
    public static void main(String[] args) throws UserDAOException {
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        User user = userDAO.getUserByEmail("mail@mail.ru");
        System.out.println(user);
    }
}