package daoInterface.dao;

import entity.UserReview;
import exception.UserReviewDAOException;

import java.util.List;

public interface UserReviewDAO {

    void addUserReview(UserReview userReview) throws UserReviewDAOException;

    UserReview getUserReviewByID(int id) throws UserReviewDAOException;

    List<UserReview> getAllReviews() throws UserReviewDAOException;

    void updateUserReview(UserReview userReview) throws UserReviewDAOException;

    void deleteUSerReviewByID(int id) throws UserReviewDAOException;

}
