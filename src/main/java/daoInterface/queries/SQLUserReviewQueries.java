package daoInterface.queries;

import daoInterface.columnnames.FilmColumnNames;
import daoInterface.columnnames.UserReviewColumnNames;

public class SQLUserReviewQueries {

    public static final String ADD_REVIEW = "INSERT INTO user_reviews ("
            + UserReviewColumnNames.COLUMN_REVIEW_FILM_ID + ","
            + UserReviewColumnNames.COLUMN_REVIEW_USER_ID + ","
            + UserReviewColumnNames.COLUMN_REVIEW_TEXT + ","
            + UserReviewColumnNames.COLUMN_REVIEW_MARK + ","
            + UserReviewColumnNames.COLUMN_REVIEW_CREATED_DATE + ") VALUES (?, ?, ?, ?)";
    public static final String GET_REVIEW = "SELECT * FROM user_reviews WHERE "
            + UserReviewColumnNames.COLUMN_REVIEW_ID + " = ?";
    public static final String GET_ALL_REVIEWS = "SELECT * FROM user_reviews";
    public static final String UPDATE_REVIEW = "UPDATE user_reviews SET "
            + UserReviewColumnNames.COLUMN_REVIEW_FILM_ID + " = ?, "
            + UserReviewColumnNames.COLUMN_REVIEW_USER_ID + " = ?, "
            + UserReviewColumnNames.COLUMN_REVIEW_TEXT + " = ?, "
            + UserReviewColumnNames.COLUMN_REVIEW_MARK + " = ?, "
            + UserReviewColumnNames.COLUMN_REVIEW_CREATED_DATE + " = ? WHERE "
            + UserReviewColumnNames.COLUMN_REVIEW_ID + " = ?";
    public static final String DELETE_REVIEW = "DELETE FROM user_reviews WHERE "
            + UserReviewColumnNames.COLUMN_REVIEW_ID + " = ?";

}
