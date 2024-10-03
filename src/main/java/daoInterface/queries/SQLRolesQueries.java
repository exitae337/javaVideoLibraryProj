package daoInterface.queries;

import daoInterface.columnnames.RolesColumnNames;

public class SQLRolesQueries {
    public static final String ADD_ROLE = "INSERT INTO user_role ("
            + RolesColumnNames.COLUMN_ROLE_NAME + ") VALUES (?)";
    public static final String GET_ROLE = "SELECT * FROM user_role WHERE "
            + RolesColumnNames.COLUMN_ROLE_ID + " = ?";
    public static final String GET_ALL_ROLES = "SELECT * FROM user_role";
    public static final String UPDATE_ROLE = "UPDATE user_role SET "
            + RolesColumnNames.COLUMN_ROLE_NAME + " = ? WHERE "
            + RolesColumnNames.COLUMN_ROLE_ID + " = ?";
    public static final String DELETE_ROLE = "DELETE FROM user_role WHERE "
            + RolesColumnNames.COLUMN_ROLE_ID + " = ?";
}
