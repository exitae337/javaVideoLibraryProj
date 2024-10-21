package entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user_reviews")
public class UserReview implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int review_id;
    @Column
    private int review_film_id;
    @Column
    private int review_user_id;
    @Column
    private String review_text;
    @Column
    private int review_mark;
    @Column
    private String review_created_date;

    public UserReview() {}

    public int getReview_film_id() {
        return review_film_id;
    }

    public int getReview_id() {
        return review_id;
    }

    public int getReview_mark() {
        return review_mark;
    }

    public int getReview_user_id() {
        return review_user_id;
    }

    public String getReview_created_date() {
        return review_created_date;
    }

    public String getReview_text() {
        return review_text;
    }

    // Setters


    public void setReview_created_date(String review_created_date) {
        this.review_created_date = review_created_date;
    }

    public void setReview_film_id(int review_film_id) {
        this.review_film_id = review_film_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public void setReview_mark(int review_mark) {
        this.review_mark = review_mark;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public void setReview_user_id(int review_user_id) {
        this.review_user_id = review_user_id;
    }
}
