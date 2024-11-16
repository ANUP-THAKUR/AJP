package com.book.BookReview;


import dao.UserDao;
import dao.BookDao;
import dao.ReviewDao;
import daoimpl.UserDaoImpl;
import daoimpl.BookDaoImpl;
import daoimpl.ReviewDaoImpl;
import entities.User;
import entities.Book;
import entities.Review;

import java.util.List;
import java.util.Scanner;

public class BookReviewApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        ReviewDao reviewDao = new ReviewDaoImpl();

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. User Management");
            System.out.println("2. Book Management");
            System.out.println("3. Review Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    userMenu(userDao, scanner);
                    break;
                case 2:
                    bookMenu(bookDao, scanner);
                    break;
                case 3:
                    reviewMenu(reviewDao, scanner);
                    break;
                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void userMenu(UserDao userDao, Scanner scanner) {
        while (true) {
            System.out.println("\nUser Management:");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. Delete User");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user email: ");
                    String email = scanner.nextLine();
                    userDao.addUser(new User(userId, name, email));
                    break;
                case 2:
                    List<User> users = userDao.getAllUsers();
                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                    } else {
                        for (User user : users) {
                            System.out.println(user);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter user ID to delete: ");
                    int deleteUserId = scanner.nextInt();
                    userDao.deleteUser(deleteUserId);
                    break;
                case 4:
                    return; // Back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void bookMenu(BookDao bookDao, Scanner scanner) {
        while (true) {
            System.out.println("\nBook Management:");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int bookChoice = scanner.nextInt();

            switch (bookChoice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    bookDao.addBook(new Book(bookId, title, author));
                    break;
                case 2:
                    List<Book> books = bookDao.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        for (Book book : books) {
                            System.out.println(book);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter book ID to delete: ");
                    int deleteBookId = scanner.nextInt();
                    bookDao.deleteBook(deleteBookId);
                    break;
                case 4:
                    return; // Back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void reviewMenu(ReviewDao reviewDao, Scanner scanner) {
        while (true) {
            System.out.println("\nReview Management:");
            System.out.println("1. Add Review");
            System.out.println("2. View Reviews by Book ID");
            System.out.println("3. Delete Review");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int reviewChoice = scanner.nextInt();

            switch (reviewChoice) {
                case 1:
                    System.out.print("Enter review ID: ");
                    int reviewId = scanner.nextInt();
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter review text: ");
                    String reviewText = scanner.nextLine();
                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    reviewDao.addReview(new Review(reviewId, bookId, userId, reviewText, rating));
                    break;
                case 2:
                    System.out.print("Enter book ID to view reviews: ");
                    int viewBookId = scanner.nextInt();
                    List<Review> reviews = reviewDao.getReviewsByBookId(viewBookId);
                    if (reviews.isEmpty()) {
                        System.out.println("No reviews found for this book.");
                    } else {
                        for (Review review : reviews) {
                            System.out.println(review);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter review ID to delete: ");
                    int deleteReviewId = scanner.nextInt();
                    reviewDao.deleteReview(deleteReviewId);
                    break;
                case 4:
                    return; // Back to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
