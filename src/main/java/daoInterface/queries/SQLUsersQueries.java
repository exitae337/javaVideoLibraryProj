package daoInterface.queries;

public class SQLUsersQueries {
    public static final String ADD_USER = "INSERT INTO users (user_fullname, user_email, user_password, user_role_id) VALUES (?, ?, ?, ?)";
    public static final String GET_USER = "SELECT * FROM users WHERE user_id = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String UPDATE_USER = "UPDATE users SET user_fullname = ?, user_email = ?, user_password = ?, user_role_id = ? WHERE user_id = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE user_id = ?";
}
