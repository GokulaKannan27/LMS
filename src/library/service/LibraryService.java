package library.service;

import library.dao.IssueLayer;
import library.models.IssueRecord;

import java.sql.SQLException;
import java.util.Scanner;

public class LibraryService {
    Scanner sc = new Scanner(System.in);
    IssueLayer issueLayer = new IssueLayer();
    int choice = 0;
    public LibraryService() throws SQLException {
        System.out.println("""
                1.IssueBook
                2.ReturnBook
                3.IssuedBook Details
                4.Exit
                """);
        while(choice!=4){
            System.out.print("Enter your Choice: ");
            choice = sc.nextInt();
            switch (choice){
                case 1 ->{
                    System.out.print("Enter the BookId: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter the MemberId: ");
                    int memberId = sc.nextInt();
                    issueLayer.issueBook(bookId,memberId);
                }
                case 2 ->{
                    System.out.print("Enter the Borrowed BookID to return: ");
                    int bookId = sc.nextInt();
                    issueLayer.returnBook(bookId);
                }
                case 3 ->
                    issueLayer.getIssuedBooks();
                case 4 -> System.out.println("Exiting...");
            }
        }
    }
}
