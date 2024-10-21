package daoInterface.implementation;


import database.HibernateUtil;
import entity.User;
import entity.UserRole;
import daoInterface.columnnames.RolesColumnNames;
import daoInterface.dao.RoleDAO;
import daoInterface.queries.SQLRolesQueries;
import database.myConnectionPool.ConnectionProxy;
import database.myConnectionPool.ConnectorToDatabase;
import exception.RoleDAOException;
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

public class RoleDAOImpl implements RoleDAO {

    private static volatile RoleDAOImpl instance = null;

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private RoleDAOImpl() {}

    @Override
    public void addRole(UserRole role) throws RoleDAOException {
        Transaction transaction = null;
        try(
                Session session = sessionFactory.openSession();
        ) {
            transaction = session.beginTransaction();
            session.persist(role);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new RoleDAOException("Problems with adding user", e.getCause());
        }
    }

    @Override
    public UserRole getRoleByID(int id) throws RoleDAOException {
        UserRole role = null;
        try(
                Session session = sessionFactory.openSession();
        ) {
            role = session.find(UserRole.class, id);
        } catch (Exception e) {
            throw new RoleDAOException("Problems with getting role by id", e.getCause());
        }
        return role;
    }

    @Override
    public List<UserRole> getAllRoles() throws RoleDAOException {
        List<UserRole> roleList = new ArrayList<>();
        try(
                Session session = sessionFactory.openSession()
        ) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserRole> cq = cb.createQuery(UserRole.class);
            Root<UserRole> root = cq.from(UserRole.class);
            cq.select(root);
            roleList = session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new RoleDAOException("Problem with getting all roles", e.getCause());
        }
        return roleList;
    }

    @Override
    public void updateRole(UserRole role) throws RoleDAOException {
        Transaction transaction = null;
        try(
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            session.merge(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RoleDAOException("Problems with updating role", e.getCause());
        }
    }

    @Override
    public void deleteRoleByID(int id) throws RoleDAOException {
        Transaction transaction = null;
        try(
                Session session = sessionFactory.openSession()
        ) {
            transaction = session.beginTransaction();
            UserRole userRole = session.find(UserRole.class, id);
            if(userRole != null) {
                session.remove(userRole);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RoleDAOException("Problems with deleting role", e.getCause());
        }
    }

    public static RoleDAOImpl getInstance() {
        RoleDAOImpl roleLocalDAO = instance;
        if(roleLocalDAO == null) {
            synchronized (RoleDAOImpl.class) {
                roleLocalDAO = instance;
                if(roleLocalDAO == null) {
                    roleLocalDAO = new RoleDAOImpl();
                    instance = roleLocalDAO;
                }
            }
        }
        return instance;
    }

}
