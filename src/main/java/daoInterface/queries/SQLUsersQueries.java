package daoInterface.queries;

import daoInterface.columnnames.UsersColumnNames;

public class SQLUsersQueries {
    public static final String ADD_USER = "INSERT INTO users ("
            + UsersColumnNames.COLUMN_USER_FULLNAME + ","
            + UsersColumnNames.COLUMN_USER_EMAIL + ","
            + UsersColumnNames.COLUMN_USER_PASSWORD + ","
            + UsersColumnNames.COLUMN_USER_ROLE_ID + ") VALUES (?, ?, ?, ?)";
    public static final String GET_USER = "SELECT * FROM users WHERE "
            + UsersColumnNames.COLUMN_USER_ID + " = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String UPDATE_USER = "UPDATE users SET "
            + UsersColumnNames.COLUMN_USER_FULLNAME + " = ?, "
            + UsersColumnNames.COLUMN_USER_EMAIL + " = ?, "
            + UsersColumnNames.COLUMN_USER_PASSWORD + " = ?, "
            + UsersColumnNames.COLUMN_USER_ROLE_ID + " = ? WHERE "
            + UsersColumnNames.COLUMN_USER_ID + " = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE "
            + UsersColumnNames.COLUMN_USER_ID + " = ?";
}
