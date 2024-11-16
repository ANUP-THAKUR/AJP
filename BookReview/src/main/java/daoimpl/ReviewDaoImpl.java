package daoimpl;

import dao.ReviewDao;
import entities.Review;
import utility.DatabaseConnection;
import utility.IDGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    @Override
    public void addReview(Review review) {
        String query = "INSERT INTO reviews (review_id, book_id, user_id, review_text, rating) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, review.getReviewId());
            statement.setInt(2, review.getBookId());
            statement.setInt(3, review.getUserId());
            statement.setString(4, review.getReviewText());
            statement.setInt(5, review.getRating());
            statement.executeUpdate();
            System.out.println("Review added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Review> getReviewsByBookId(int bookId) {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM reviews WHERE book_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    reviews.add(new Review(
                            resultSet.getInt("review_id"),
                            resultSet.getInt("book_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("review_text"),
                            resultSet.getInt("rating")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public void deleteReview(int reviewId) {
        String query = "DELETE FROM reviews WHERE review_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reviewId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Review deleted successfully.");
            } else {
                System.out.println("Review not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
