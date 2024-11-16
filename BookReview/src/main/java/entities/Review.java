package entities;

public class Review {
    private int reviewId;
    private int bookId;
    private int userId;
    private String reviewText;
    private int rating;

    // Constructors
    public Review(int reviewId, int bookId, int userId, String reviewText, int rating) {
        this.reviewId = reviewId;
        this.bookId = bookId;
        this.userId = userId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    // Getters and Setters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
