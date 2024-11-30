package daoimpl;

import dao.ReviewDao;
import entities.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtil;

import java.util.List;

public class ReviewDaoImpl implements ReviewDao {

    @Override
    public void addReview(Review review) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(review);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Review> getReviewsByBookId(int bookId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Review> reviews = null;
        try {
            reviews = session.createQuery("FROM Review WHERE bookId = :bookId", Review.class)
                    .setParameter("bookId", bookId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return reviews;
    }

    @Override
    public void deleteReview(int reviewId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Review review = session.get(Review.class, reviewId);
            if (review != null) {
                session.delete(review);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
