package daoInterface.implementation;

import database.HibernateUtil;
import entity.GenreFilm;
import daoInterface.columnnames.GenreFilmColumnNames;
import daoInterface.dao.GenreFilmDAO;
import daoInterface.queries.SQLGenreFilmQueries;
import database.myConnectionPool.ConnectionProxy;
import database.myConnectionPool.ConnectorToDatabase;
import entity.User;
import exception.GenreFilmDAOException;
import exception.UserDAOException;
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

public class GenreFilmDAOImpl implements GenreFilmDAO {

    private static volatile GenreFilmDAOImpl instance = null;

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private GenreFilmDAOImpl() {}

    @Override
    public void addGenreFilm(GenreFilm genreFilm) throws GenreFilmDAOException {
        Transaction transaction = null;
        try(
                Session session = sessionFactory.openSession();
        ) {
            transaction = session.beginTransaction();
            session.persist(genreFilm);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new GenreFilmDAOException("Problems with adding new genre!", e.getCause());
        }
    }

    @Override
    public GenreFilm getGenreFilmByID(int id) throws GenreFilmDAOException {
        GenreFilm genreFilm = null;
        try (
                Session session = sessionFactory.openSession();
        ) {
            genreFilm = session.find(GenreFilm.class, id);
        } catch (Exception e) {
            throw new GenreFilmDAOException("Error in getting genre!", e.getCause());
        }
        return genreFilm;
    }

    @Override
    public List<GenreFilm> getAllGenres() throws GenreFilmDAOException {
        List<GenreFilm> genreFilmList = new ArrayList<>();
        try (
                Session session = sessionFactory.openSession()
        ) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<GenreFilm> cq = cb.createQuery(GenreFilm.class);
            Root<GenreFilm> root = cq.from(GenreFilm.class);
            cq.select(root);
            genreFilmList = session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new GenreFilmDAOException("Problem in getting all genres!", e.getCause());
        }
        return genreFilmList;
    }

    @Override
    public void updateGenreFilm(GenreFilm genreFilm) throws GenreFilmDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            session.merge(genreFilm);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new GenreFilmDAOException("Problems with updating role", e.getCause());
        }
    }

    @Override
    public void deleteGenreFilmByID(int id) throws GenreFilmDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
           transaction = session.beginTransaction();
           GenreFilm genreFilm = session.find(GenreFilm.class, id);
           if (genreFilm != null) {
               session.remove(genreFilm);
           }
           transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new GenreFilmDAOException("problems with deleting genre!", e.getCause());
        }
    }

    public static GenreFilmDAOImpl getInstance() {
        GenreFilmDAOImpl localGenreFilm = instance;
        if (localGenreFilm == null) {
            synchronized (GenreFilmDAOImpl.class) {
                localGenreFilm = instance;
                if (localGenreFilm == null) {
                    localGenreFilm = new GenreFilmDAOImpl();
                    instance = localGenreFilm;
                }
            }
        }
        return instance;
    }

}
