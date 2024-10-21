package daoInterface.implementation;

import database.HibernateUtil;
import entity.Film;
import daoInterface.columnnames.FilmColumnNames;
import daoInterface.dao.FilmDAO;
import daoInterface.queries.SQLFilmsQueries;
import database.myConnectionPool.ConnectionProxy;
import database.myConnectionPool.ConnectorToDatabase;
import entity.User;
import exception.FilmDAOException;
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

public class FilmDAOImpl implements FilmDAO {

    private static volatile FilmDAOImpl instance = null;
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private FilmDAOImpl() {}

    @Override
    public void addFilm(Film film) throws FilmDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            session.persist(film);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new FilmDAOException("Problem in adding film", e.getCause());
        }
    }

    @Override
    public Film getFilmByID(int id) throws FilmDAOException {
        Film film = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            film = session.find(Film.class, id);
        } catch (Exception e) {
            throw new FilmDAOException("Problem in getting film!", e.getCause());
        }
        return film;
    }

    @Override
    public List<Film> getAllFilms() throws FilmDAOException {
        List<Film> filmList = new ArrayList<>();
        try(
                Session session = sessionFactory.openSession()
        ) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Film> cq = cb.createQuery(Film.class);
            Root<Film> root = cq.from(Film.class);
            cq.select(root);
            filmList = session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new FilmDAOException("Problem in getting all films!", e.getCause());
        }
        return filmList;
    }

    @Override
    public void updateFilm(Film film) throws FilmDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
           transaction = session.beginTransaction();
           session.merge(film);
           transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new FilmDAOException("Problem in updating film", e.getCause());
        }
    }

    @Override
    public void deleteFilmByID(int id) throws FilmDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            Film film = session.find(Film.class, id);
            if (film != null) {
                session.remove(film);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new FilmDAOException("Problems in deleting film!", e.getCause());
        }
    }

    public static FilmDAOImpl getInstance() {
        FilmDAOImpl localFilmInstance = instance;
        if (localFilmInstance == null) {
            synchronized (FilmDAOImpl.class) {
                localFilmInstance = instance;
                if(localFilmInstance == null) {
                    localFilmInstance = new FilmDAOImpl();
                    instance = localFilmInstance;
                }
            }
        }
        return instance;
    }
}
