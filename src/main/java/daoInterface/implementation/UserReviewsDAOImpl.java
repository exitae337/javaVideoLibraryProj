package daoInterface.implementation;

import Entities.UserReview;
import daoInterface.columnnames.UserReviewColumnNames;
import daoInterface.dao.UserReviewDAO;
import daoInterface.queries.SQLUserReviewQueries;
import database.ConnectionProxy;
import database.ConnectorToDatabase;
import exceptions.UserReviewDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserReviewsDAOImpl implements UserReviewDAO {

    private static volatile UserReviewsDAOImpl instance = null;

    private static final int ERROR_CODE_CONCURRENCE = 1062;

    private final ConnectorToDatabase connectorToDatabase = ConnectorToDatabase.getInstance();

    @Override
    public void addUserReview(UserReview userReview) throws UserReviewDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLUserReviewQueries.ADD_REVIEW)
        ) {
            statement.setInt(1, userReview.getFilmID());
            statement.setInt(2, userReview.getUserID());
            statement.setString(3, userReview.getTextReview());
            statement.setInt(4, userReview.getMark());
            statement.setString(5, userReview.getCreateDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UserReviewDAOException("Problem with adding review", e.getCause());
        }
    }

    @Override
    public UserReview getUserReviewByID(int id) throws UserReviewDAOException {
        UserReview userReview = null;
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLUserReviewQueries.GET_REVIEW)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                userReview = new UserReview();
                userReview.setId(rs.getInt(UserReviewColumnNames.COLUMN_REVIEW_ID));
                userReview.setFilmID(rs.getInt(UserReviewColumnNames.COLUMN_REVIEW_FILM_ID));
                userReview.setUserID(rs.getInt(UserReviewColumnNames.COLUMN_REVIEW_USER_ID));
                userReview.setTextReview(rs.getString(UserReviewColumnNames.COLUMN_REVIEW_TEXT));
                userReview.setMark(rs.getInt(UserReviewColumnNames.COLUMN_REVIEW_MARK));
                userReview.setCreateDate(rs.getString(UserReviewColumnNames.COLUMN_REVIEW_CREATED_DATE));
            }
        } catch (SQLException e) {
            throw new UserReviewDAOException("Problem with getting review", e.getCause());
        }
        return userReview;
    }

    @Override
    public List<UserReview> getAllReviews() throws UserReviewDAOException {
        List<UserReview> userReviewList = new ArrayList<>();
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLUserReviewQueries.GET_ALL_REVIEWS)
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserReview userReview = new UserReview();
                userReview.setId(rs.getInt(UserReviewColumnNames.COLUMN_REVIEW_ID));
                userReview.setFilmID(rs.getInt(UserReviewColumnNames.COLUMN_REVIEW_FILM_ID));
                userReview.setUserID(rs.getInt(UserReviewColumnNames.COLUMN_REVIEW_USER_ID));
                userReview.setTextReview(rs.getString(UserReviewColumnNames.COLUMN_REVIEW_TEXT));
                userReview.setMark(rs.getInt(UserReviewColumnNames.COLUMN_REVIEW_MARK));
                userReview.setCreateDate(rs.getString(UserReviewColumnNames.COLUMN_REVIEW_CREATED_DATE));
                userReviewList.add(userReview);
            }
        } catch (SQLException e) {
            throw new UserReviewDAOException("Problem with getting all reviews!", e.getCause());
        }
        return userReviewList;
    }

    @Override
    public void updateUserReview(UserReview userReview) throws UserReviewDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLUserReviewQueries.UPDATE_REVIEW)
        ) {
            statement.setInt(1, userReview.getFilmID());
            statement.setInt(2, userReview.getUserID());
            statement.setString(3, userReview.getTextReview());
            statement.setInt(4, userReview.getMark());
            statement.setString(5, userReview.getCreateDate());
            statement.setInt(6, userReview.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UserReviewDAOException("Problems with update review", e.getCause());
        }
    }

    @Override
    public void deleteUSerReviewByID(int id) throws UserReviewDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLUserReviewQueries.DELETE_REVIEW)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
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
