package daoInterface.implementation;

import database.HibernateUtil;
import entity.ActorOrDirector;
import daoInterface.columnnames.ActorOrDirectorColumnNames;
import daoInterface.dao.ActorOrDirectorDAO;
import daoInterface.queries.SQLActorOrDirectorQueries;
import database.myConnectionPool.ConnectionProxy;
import database.myConnectionPool.ConnectorToDatabase;
import entity.User;
import exception.ActorOrDirectorDAOException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorOrDirectorDAOImpl implements ActorOrDirectorDAO {

    private static volatile ActorOrDirectorDAOImpl instance = null;

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private ActorOrDirectorDAOImpl() {}

    @Override
    public void addActorOrDirector(ActorOrDirector actorOrDirector) throws ActorOrDirectorDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            session.persist(actorOrDirector);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ActorOrDirectorDAOException("Problem in adding actor or director!", e.getCause());
        }
    }

    @Override
    public ActorOrDirector getActorOrDirectorByID(int id) throws ActorOrDirectorDAOException {
        ActorOrDirector actorOrDirector = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            actorOrDirector = session.find(ActorOrDirector.class, id);
        } catch (Exception e) {
            throw new ActorOrDirectorDAOException("Problem in getting actor or director", e.getCause());
        }
        return actorOrDirector;
    }

    @Override
    public List<ActorOrDirector> getAllActorsOrDirectors() throws ActorOrDirectorDAOException {
        List<ActorOrDirector> actorOrDirectorList = new ArrayList<>();
        try (
                Session session = sessionFactory.openSession()
        ) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ActorOrDirector> cq = cb.createQuery(ActorOrDirector.class);
            Root<ActorOrDirector> root = cq.from(ActorOrDirector.class);
            cq.select(root);
            actorOrDirectorList = session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new ActorOrDirectorDAOException("problem in getting all actors or directors", e.getCause());
        }
        return actorOrDirectorList;
    }

    @Override
    public void updateActorOrDirector(ActorOrDirector actorOrDirector) throws ActorOrDirectorDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            session.merge(actorOrDirector);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new ActorOrDirectorDAOException("Problem in updating actor or director", e.getCause());
        }
    }

    @Override
    public void deleteActorOrDirectorByID(int id) throws ActorOrDirectorDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            ActorOrDirector actorOrDirector = session.find(ActorOrDirector.class, id);
            if (actorOrDirector != null) {
                session.remove(actorOrDirector);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
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
