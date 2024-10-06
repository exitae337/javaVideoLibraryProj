package daoInterface.implementation;

import Entities.ActorOrDirector;
import daoInterface.columnnames.ActorOrDirectorColumnNames;
import daoInterface.dao.ActorOrDirectorDAO;
import daoInterface.queries.SQLActorOrDirectorQueries;
import database.ConnectionProxy;
import database.ConnectorToDatabase;
import exceptions.ActorOrDirectorDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorOrDirectorDAOImpl implements ActorOrDirectorDAO {

    private static volatile ActorOrDirectorDAOImpl instance = null;
    private static final int ERROR_CODE_CONCURRENCE = 1062;
    private final ConnectorToDatabase connectorToDatabase = ConnectorToDatabase.getInstance();

    private ActorOrDirectorDAOImpl() {}

    @Override
    public void addActorOrDirector(ActorOrDirector actorOrDirector) throws ActorOrDirectorDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLActorOrDirectorQueries.ADD_PERSON)
        ) {
            statement.setString(1, actorOrDirector.getPersonFullName());
            statement.setString(2, actorOrDirector.getPersonType());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ActorOrDirectorDAOException("Problem in adding actor or director!", e.getCause());
        }
    }

    @Override
    public ActorOrDirector getActorOrDirectorByID(int id) throws ActorOrDirectorDAOException {
        ActorOrDirector actorOrDirector = null;
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLActorOrDirectorQueries.GET_PERSON)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
               actorOrDirector = new ActorOrDirector();
               actorOrDirector.setId(rs.getInt(ActorOrDirectorColumnNames.COLUMN_PERSON_ID));
               actorOrDirector.setPersonFullName(rs.getString(ActorOrDirectorColumnNames.COLUMN_PERSON_FULLNAME));
               actorOrDirector.setPersonType(rs.getString(ActorOrDirectorColumnNames.COLUMN_PERSON_ROLE));
            }
        } catch (SQLException e) {
            throw new ActorOrDirectorDAOException("Problem in getting actor or director", e.getCause());
        }
        return actorOrDirector;
    }

    @Override
    public List<ActorOrDirector> getAllActorsOrDirectors() throws ActorOrDirectorDAOException {
        List<ActorOrDirector> actorOrDirectorList = new ArrayList<>();
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLActorOrDirectorQueries.GET_ALL_PEOPLE)
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ActorOrDirector actorOrDirector = new ActorOrDirector();
                actorOrDirector.setId(rs.getInt(ActorOrDirectorColumnNames.COLUMN_PERSON_ID));
                actorOrDirector.setPersonFullName(rs.getString(ActorOrDirectorColumnNames.COLUMN_PERSON_FULLNAME));
                actorOrDirector.setPersonType(rs.getString(ActorOrDirectorColumnNames.COLUMN_PERSON_ROLE));
                actorOrDirectorList.add(actorOrDirector);
            }
        } catch (SQLException e) {
            throw new ActorOrDirectorDAOException("problem in getting all actors or directors", e.getCause());
        }
        return actorOrDirectorList;
    }

    @Override
    public void updateActorOrDirector(ActorOrDirector actorOrDirector) throws ActorOrDirectorDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLActorOrDirectorQueries.UPDATE_PERSON)
        ) {
            statement.setString(1, actorOrDirector.getPersonFullName());
            statement.setString(2, actorOrDirector.getPersonType());
            statement.setInt(3, actorOrDirector.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ActorOrDirectorDAOException("Problem in updating actor or director", e.getCause());
        }
    }

    @Override
    public void deleteActorOrDirectorByID(int id) throws ActorOrDirectorDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLActorOrDirectorQueries.DELETE_PERSON)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ActorOrDirectorDAOException("Problems in deleting actor or director!", e.getCause());
        }
    }

    public static ActorOrDirectorDAOImpl getInstance() {
        ActorOrDirectorDAOImpl localActorOrDirectorInstance = instance;
        if(localActorOrDirectorInstance == null) {
            synchronized (ActorOrDirectorDAOImpl.class) {
                localActorOrDirectorInstance = instance;
                if(localActorOrDirectorInstance == null) {
                    localActorOrDirectorInstance = new ActorOrDirectorDAOImpl();
                    instance = localActorOrDirectorInstance;
                }
            }
        }
        return instance;
    }

}
