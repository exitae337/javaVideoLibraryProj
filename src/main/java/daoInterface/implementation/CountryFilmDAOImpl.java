package daoInterface.implementation;

import database.HibernateUtil;
import entity.CountryFilm;
import daoInterface.columnnames.CountryFilmColumnNames;
import daoInterface.dao.CountryFilmDAO;
import daoInterface.queries.SQLCountryFilmQueries;
import database.myConnectionPool.ConnectionProxy;
import database.myConnectionPool.ConnectorToDatabase;
import entity.User;
import exception.CountryFilmDAOException;
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

public class CountryFilmDAOImpl implements CountryFilmDAO {

    private static volatile CountryFilmDAOImpl instance = null;
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private CountryFilmDAOImpl() {}

    @Override
    public void addCountryFilm(CountryFilm countryFilm) throws CountryFilmDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            session.persist(countryFilm);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CountryFilmDAOException("Problems with adding Country!", e.getCause());

        }
    }

    @Override
    public CountryFilm getCountryFilmByID(int id) throws CountryFilmDAOException {
        CountryFilm countryFilm = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            countryFilm = session.find(CountryFilm.class, id);
        } catch (Exception e) {
            throw new CountryFilmDAOException("Problems with getting Country!", e.getCause());
        }
        return countryFilm;
    }

    @Override
    public List<CountryFilm> getAllCountries() throws CountryFilmDAOException {
        List<CountryFilm> countryFilmList = new ArrayList<>();
        try (
                Session session = sessionFactory.openSession()
        ) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CountryFilm> cq = cb.createQuery(CountryFilm.class);
            Root<CountryFilm> root = cq.from(CountryFilm.class);
            cq.select(root);
            countryFilmList = session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new CountryFilmDAOException("Problem eih getting all users!", e.getCause());
        }
        return countryFilmList;
    }

    @Override
    public void updateCountryFilm(CountryFilm countryFilm) throws CountryFilmDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            session.merge(countryFilm);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CountryFilmDAOException("Problem in updating Country", e.getCause());
        }
    }

    @Override
    public void deleteCountryFilmByID(int id) throws CountryFilmDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            CountryFilm countryFilm = session.find(CountryFilm.class, id);
            if (countryFilm != null) {
                session.remove(countryFilm);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CountryFilmDAOException("Problem with deleting Country", e.getCause());
        }
    }

    public static CountryFilmDAOImpl getInstance() {
        CountryFilmDAOImpl localCountryFilmInstance = instance;
        if (localCountryFilmInstance == null) {
            synchronized (CountryFilmDAOImpl.class) {
                localCountryFilmInstance = instance;
                if (localCountryFilmInstance == null) {
                    localCountryFilmInstance = new CountryFilmDAOImpl();
                    instance = localCountryFilmInstance;
                }
            }
        }
        return instance;
    }

}
