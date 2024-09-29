package daoInterface.queries;

public class SQLRolesQueries {
    public static final String ADD_ROLE = "INSERT INTO user_role (role_name) VALUES (?)";
    public static final String GET_ROLE = "SELECT * FROM user_role WHERE role_id = ?";
    public static final String GET_ALL_ROLES = "SELECT * FROM user_role";
    public static final String UPDATE_ROLE = "UPDATE user_role SET role_name = ? WHERE role_id = ?";
    public static final String DELETE_ROLE = "DELETE FROM user_role WHERE role_id = ?";
}
