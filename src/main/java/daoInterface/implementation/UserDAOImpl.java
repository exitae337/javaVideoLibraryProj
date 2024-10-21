package daoInterface.implementation;

import database.HibernateUtil;
import entity.User;
import daoInterface.dao.UserDAO;
import exception.UserDAOException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static volatile UserDAOImpl instance = null;
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private UserDAOImpl() {}

    @Override
    public void addUser(User user) throws UserDAOException {
        Transaction transaction = null;
        try(
                Session session = sessionFactory.openSession();
        ) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new UserDAOException("Problems with adding new user!", e.getCause());
        }
    }

    @Override
    public User getUserByID(int id) throws UserDAOException {
        User user = null;
        try(
                Session session = sessionFactory.openSession()
        ) {
            user = session.find(User.class, id);
        } catch (Exception e) {
            throw new UserDAOException("Problems with getting user by id", e.getCause());
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws UserDAOException {
        List<User> userList = new ArrayList<>();
        try(
                Session session = sessionFactory.openSession();
        ) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);
            userList = session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new UserDAOException("Problems with getting all users", e.getCause());
        }
        return userList;
    }

    @Override
    public void updateUser(User user) throws UserDAOException {
        Transaction transaction = null;
        try(
                Session session = sessionFactory.openSession();
        ) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new UserDAOException("Problems with updating user information", e.getCause());
        }
    }

    @Override
    public void deleteUserByID(int id) throws UserDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession();
        ) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new UserDAOException("Problems with deleting user", e.getCause());
        }
    }

    @Override
    public User getUserByEmail(String email) throws UserDAOException {
        User user = null;
        try (
                Session session = sessionFactory.openSession();
        ) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get("user_email"), email));
            user = session.createQuery(cq).uniqueResult();
        } catch ( Exception e) {
            throw new UserDAOException(e.getMessage());
        }
        return user;
    }

    public static UserDAOImpl getInstance() {
        UserDAOImpl userLocalDAO = instance;
        if (userLocalDAO == null) {
            synchronized (UserDAOImpl.class) {
                userLocalDAO = instance;
                if (userLocalDAO == null) {
                    userLocalDAO = new UserDAOImpl();
                    instance = userLocalDAO;
                }
            }
        }
        return instance;
    }

}
