import Entities.User;
import database.ConnectorToDatabase;
import exceptions.UserDAOException;
import services.UsersService;

// FOR TESTING
public class Main {
    public static void main(String[] args) throws UserDAOException {
        ConnectorToDatabase connectorToDatabase = ConnectorToDatabase.getInstance();
       if(connectorToDatabase.connectionPoolInitialized()) {
           UsersService usersService = UsersService.getInstance();
           System.out.println(usersService.getAllUsers());
       }
    }
}