package dao;

import entities.Review;

import java.util.List;

public interface ReviewDao {
    void addReview(Review review);

    List<Review> getReviewsByBookId(int bookId);

    void deleteReview(int reviewId);
}
