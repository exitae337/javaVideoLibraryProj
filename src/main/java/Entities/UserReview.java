package Entities;

public class UserReview {
    private int id;
    private int film_id;
    private int user_id;
    private String text_review;
    private int mark;
    private String createDate;

    public UserReview() {}

    // Getters

    public int getId() {
        return id;
    }

    public int getFilmID() {
        return film_id;
    }

    public int getUserID() {
        return user_id;
    }


    public String getTextReview() {
        return text_review;
    }

    public int getMark() {
        return mark;
    }

    public String getCreateDate() {
        return createDate;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setFilmID(int film_id) {
        this.film_id = film_id;
    }

    public void setUserID(int user_id) {
        this.user_id = user_id;
    }

    public void setTextReview(String text_review) {
        this.text_review = text_review;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
