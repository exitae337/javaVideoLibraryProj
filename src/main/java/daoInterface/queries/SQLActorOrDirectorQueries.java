package daoInterface.queries;

import daoInterface.columnnames.ActorOrDirectorColumnNames;
import daoInterface.columnnames.RolesColumnNames;
import daoInterface.columnnames.UsersColumnNames;

public class SQLActorOrDirectorQueries {

    public static final String ADD_PERSON = "INSERT INTO actors_and_directors ("
            + ActorOrDirectorColumnNames.COLUMN_PERSON_FULLNAME + ","
            + ActorOrDirectorColumnNames.COLUMN_PERSON_ROLE + ") VALUES (?, ?)";
    public static final String GET_PERSON = "SELECT * FROM actors_and_directors WHERE "
            + ActorOrDirectorColumnNames.COLUMN_PERSON_ID + " = ?";
    public static final String GET_ALL_PEOPLE = "SELECT * FROM actors_and_directors";
    public static final String UPDATE_PERSON = "UPDATE actors_and_directors SET "
            + ActorOrDirectorColumnNames.COLUMN_PERSON_FULLNAME + " = ?, "
            + ActorOrDirectorColumnNames.COLUMN_PERSON_ROLE + " = ? WHERE "
            + ActorOrDirectorColumnNames.COLUMN_PERSON_ID + " = ?";
    public static final String DELETE_PERSON = "DELETE FROM actors_and_directors WHERE "
            + ActorOrDirectorColumnNames.COLUMN_PERSON_ID + " = ?";

}
