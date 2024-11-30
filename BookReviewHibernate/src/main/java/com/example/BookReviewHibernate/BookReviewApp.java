package com.example.BookReviewHibernate;

import dao.UserDao;
import dao.BookDao;
import dao.ReviewDao;
import daoimpl.UserDaoImpl;
import daoimpl.BookDaoImpl;
import daoimpl.ReviewDaoImpl;
import entities.User;
import entities.Book;
import entities.Review;
import utility.HibernateUtil;

import java.util.List;
import java.util.Scanner;
import utility.emailValidation;

public class BookReviewApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        ReviewDao reviewDao = new ReviewDaoImpl();

        int choice;
        do {
            System.out.println("\n=== Book Review Platform ===");
            System.out.println("1. User Operations");
            System.out.println("2. Book Operations");
            System.out.println("3. Review Operations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userMenu(scanner, userDao);
                    break;
                case 2:
                    bookMenu(scanner, bookDao);
                    break;
                case 3:
                    reviewMenu(scanner, reviewDao);
                    break;
                case 0:
                    System.out.println("Exiting the application...");
                    HibernateUtil.closeSessionFactory();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    // User Operations Menu
    private static void userMenu(Scanner scanner, UserDao userDao) {
        int choice;
        do {
            System.out.println("\n=== User Operations ===");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. Delete User");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addUser(scanner, userDao);
                    break;
                case 2:
                    viewUsers(userDao);
                    break;
                case 3:
                    deleteUser(scanner, userDao);
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // Add User
    private static void addUser(Scanner scanner, UserDao userDao) {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();
       String email=null;
        boolean isValid = false;
        
        while (!isValid) {
            System.out.print("Enter User Email: ");
            email = scanner.nextLine();
            
            if (emailValidation.isValidEmail(email)) {
                isValid = true;  
            } else {
                System.out.println("Invalid email format! Please enter a valid email (e.g., example@domain.com).");
            }
        }
        userDao.addUser(new User(userId, name, email));
        System.out.println("User added successfully!");
    }

    

	// View All Users
    private static void viewUsers(UserDao userDao) {
        List<User> users = userDao.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("\n===== User List =====");
            System.out.printf("%-10s %-30s %-30s\n", "User ID", "Username", "Email");
            for (User user : users) {
                System.out.printf("%-10d %-30s %-30s\n", user.getUserId(), user.getUsername(), user.getEmail());
            }
        }
    }

    // Delete User
    private static void deleteUser(Scanner scanner, UserDao userDao) {
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        userDao.deleteUser(userId);
        System.out.println("User deleted successfully!");
    }

    // Book Operations Menu
    private static void bookMenu(Scanner scanner, BookDao bookDao) {
        int choice;
        do {
            System.out.println("\n=== Book Operations ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Delete Book");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook(scanner, bookDao);
                    break;
                case 2:
                    viewBooks(bookDao);
                    break;
                case 3:
                    deleteBook(scanner, bookDao);
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // Add Book
    private static void addBook(Scanner scanner, BookDao bookDao) {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();
        bookDao.addBook(new Book(bookId, title, author));
        System.out.println("Book added successfully!");
    }

    // View All Books
    private static void viewBooks(BookDao bookDao) {
        List<Book> books = bookDao.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("\n===== Book List =====");
            System.out.printf("%-10s %-30s %-30s\n", "Book ID", "Title", "Author");
            for (Book book : books) {
                System.out.printf("%-10d %-30s %-30s\n", book.getBookId(), book.getTitle(), book.getAuthor());
            }
        }
    }

    // Delete Book
    private static void deleteBook(Scanner scanner, BookDao bookDao) {
        System.out.print("Enter Book ID to delete: ");
        int bookId = scanner.nextInt();
        bookDao.deleteBook(bookId);
        System.out.println("Book deleted successfully!");
    }

    // Review Operations Menu
    private static void reviewMenu(Scanner scanner, ReviewDao reviewDao) {
        int choice;
        do {
            System.out.println("\n=== Review Operations ===");
            System.out.println("1. Add Review");
            System.out.println("2. View Reviews by Book ID");
            System.out.println("3. Delete Review");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addReview(scanner, reviewDao);
                    break;
                case 2:
                    viewReviews(scanner, reviewDao);
                    break;
                case 3:
                    deleteReview(scanner, reviewDao);
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // Add Review
    private static void addReview(Scanner scanner, ReviewDao reviewDao) {
        System.out.print("Enter Review ID: ");
        int reviewId = scanner.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Review Text: ");
        String reviewText = scanner.nextLine();
        System.out.print("Enter Rating (1-5): ");
        int rating = scanner.nextInt();
        reviewDao.addReview(new Review(reviewId, bookId, userId, reviewText, rating));
        System.out.println("Review added successfully!");
    }

    // View Reviews by Book ID
    private static void viewReviews(Scanner scanner, ReviewDao reviewDao) {
        System.out.print("Enter Book ID to view reviews: ");
        int bookId = scanner.nextInt();
        List<Review> reviews = reviewDao.getReviewsByBookId(bookId);
        if (reviews.isEmpty()) {
            System.out.println("No reviews found for this book.");
        } else {
            System.out.println("\n===== Reviews List =====");
            System.out.printf("%-10s %-10s %-20s %-10s\n", "Review ID", "Book ID", "Review Text", "Rating");
            for (Review review : reviews) {
                System.out.printf("%-10d %-10d %-20s %-10d\n", review.getReviewId(), review.getBookId(), review.getReviewText(), review.getRating());
            }
        }
    }

    // Delete Review
    private static void deleteReview(Scanner scanner, ReviewDao reviewDao) {
        System.out.print("Enter Review ID to delete: ");
        int reviewId = scanner.nextInt();
        reviewDao.deleteReview(reviewId);
        System.out.println("Review deleted successfully!");
    }
}
