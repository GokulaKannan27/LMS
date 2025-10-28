package library;

import library.dao.BookLayer;
import library.models.Book;
import library.service.LibraryService;
import library.service.MemberService;

import java.sql.SQLException;
import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        BookLayer bookLayer = new BookLayer();

        System.out.println("""
                1.Books Section
                2.Members Section
                3.IssuedBook Section
                4.Exit
                """);
        int choice = 0;
        while(choice!=4){
            System.out.print("Enter your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1 ->{
                    System.out.println("""
                            \\n===== Library Management System =====
                                                1. Add New Book
                                                2. Update Book
                                                3. Delete Book
                                                4. View All Books
                                                5. Search Book by Title
                                                6. Exit
                                                Enter your choice:
                            """);
                    int ch = 0;
                    while(ch!=6){
                        ch = sc.nextInt();
                        sc.nextLine();
                        switch (ch){
                            case 1 ->{
                                Book book = new Book();
                                System.out.print("Enter Book Title: ");
                                book.setTitle(sc.nextLine());

                                System.out.print("Author name: ");
                                book.setAuthor(sc.nextLine());

                                System.out.print("Publisher: ");
                                book.setPublisher(sc.nextLine());

                                System.out.print("Enter Availability(True or False) : ");
                                book.setAvailable(sc.nextBoolean());
                                bookLayer.addBook(book);
                            }
                            case 2 ->{
                                Book book = new Book();

                                System.out.print("Enter Book ID to update: ");
                                book.setId(sc.nextInt());
                                sc.nextLine(); // clear buffer

                                System.out.print("Enter new Title: ");
                                book.setTitle(sc.nextLine());

                                System.out.print("Enter new Author: ");
                                book.setAuthor(sc.nextLine());

                                System.out.print("Enter new Publisher: ");
                                book.setPublisher(sc.nextLine());

                                System.out.print("Enter new Availability: ");
                                book.setAvailable(sc.nextBoolean());

                                bookLayer.updateBook(book);
                            }
                            case 3 ->{
                               System.out.println("Enter the id: ");
                               int id = sc.nextInt();
                               bookLayer.deleteBook(id);
                            }
                            case 4 -> bookLayer.getAllBooks();
                            case 5 ->{
                                System.out.println("Enter title to search book: ");
                                String title = sc.nextLine();
                                bookLayer.searchBookByTitle(title);
                            }
                        }
                    }
                }
                case 2 ->{
                    MemberService memberService = new MemberService();
                }
                case 3 -> {LibraryService libraryService = new LibraryService();}
                case 4 ->
                    System.out.println("Exiting the System....");


            }
        }
    }
}
