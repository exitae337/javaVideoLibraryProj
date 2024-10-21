package daoInterface.implementation;

import database.HibernateUtil;
import entity.User;
import entity.UserReview;
import daoInterface.dao.UserReviewDAO;
import daoInterface.queries.SQLUserReviewQueries;
import database.myConnectionPool.ConnectionProxy;
import database.myConnectionPool.ConnectorToDatabase;
import exception.UserReviewDAOException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserReviewsDAOImpl implements UserReviewDAO {

    private static volatile UserReviewsDAOImpl instance = null;
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void addUserReview(UserReview userReview) throws UserReviewDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            session.persist(userReview);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new UserReviewDAOException("Problem with adding review", e.getCause());
        }
    }

    @Override
    public UserReview getUserReviewByID(int id) throws UserReviewDAOException {
        UserReview userReview = null;
        try (
                Session session = sessionFactory.openSession();
        ) {
            userReview = session.find(UserReview.class, id);
        } catch (Exception e) {
            throw new UserReviewDAOException("Problem with getting review", e.getCause());
        }
        return userReview;
    }

    @Override
    public List<UserReview> getAllReviews() throws UserReviewDAOException {
        List<UserReview> userReviewList = new ArrayList<>();
        try (
                Session session = sessionFactory.openSession();
        ) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserReview> cq = cb.createQuery(UserReview.class);
            Root<UserReview> root = cq.from(UserReview.class);
            cq.select(root);
            userReviewList = session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new UserReviewDAOException("Problem with getting all reviews!", e.getCause());
        }
        return userReviewList;
    }

    @Override
    public void updateUserReview(UserReview userReview) throws UserReviewDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession();
        ) {
            transaction = session.beginTransaction();
            session.merge(userReview);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new UserReviewDAOException("Problems with update review", e.getCause());
        }
    }

    @Override
    public void deleteUSerReviewByID(int id) throws UserReviewDAOException {
        Transaction transaction = null;
        try (
                Session session = sessionFactory.openSession();
        ) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            if(user != null) {
                session.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new UserReviewDAOException("Problems with deleting review", e.getCause());
        }
    }

    public static UserReviewsDAOImpl getInstance() {
        UserReviewsDAOImpl localUserReviewInstance = instance;
        if(localUserReviewInstance == null) {
            synchronized (UserReviewsDAOImpl.class) {
                localUserReviewInstance = instance;
                if(localUserReviewInstance == null) {
                    localUserReviewInstance = new UserReviewsDAOImpl();
                    instance = localUserReviewInstance;
                }
            }
        }
        return instance;
    }

}
