package daoInterface.dao;

import entity.ActorOrDirector;
import exception.ActorOrDirectorDAOException;

import java.util.List;

public interface ActorOrDirectorDAO {

    void addActorOrDirector(ActorOrDirector actorOrDirector) throws ActorOrDirectorDAOException;

    ActorOrDirector getActorOrDirectorByID(int id) throws ActorOrDirectorDAOException;

    List<ActorOrDirector> getAllActorsOrDirectors() throws ActorOrDirectorDAOException;

    void updateActorOrDirector(ActorOrDirector actorOrDirector) throws ActorOrDirectorDAOException;

    void deleteActorOrDirectorByID(int id) throws ActorOrDirectorDAOException;

}
