package library.dao;

import db.ConnectionClass;

import java.sql.*;

public class IssueLayer {
    Connection con = ConnectionClass.getConnection();
    PreparedStatement pstmt;
    private final String query = "CREATE TABLE IF NOT EXISTS issueBooks (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "bookId INT, " +
            "memberId INT, " +
            "issueDate DATE DEFAULT (CURRENT_DATE),"+
            "returnDate DATE DEFAULT (DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY)),"+
            "FOREIGN KEY (bookId) REFERENCES Books(id), " +
            "FOREIGN KEY (memberId) REFERENCES Member(id)" +
            ")";
    {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Issue table created or already exists.");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Issue table: " + e.getMessage());
        }
    }
    public void issueBook(int bookId, int memberId) throws SQLException{
        pstmt = con.prepareStatement("INSERT INTO issueBooks(bookId,memberId) VALUES(?,?)");
        pstmt.setInt(1, bookId);
        pstmt.setInt(2,memberId);
        pstmt.executeUpdate();
        System.out.println("Book Issued Info Registered Successfully");
    }
    public void returnBook(int bookId) throws SQLException{
        pstmt = con.prepareStatement("DELETE FROM issueBooks WHERE bookId = ?");
        pstmt.setInt(1, bookId);
        int rows = pstmt.executeUpdate();

        if (rows > 0) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("No issued record found for Book ID: " + bookId);
        }
    }
    public void getIssuedBooks() throws SQLException{
        pstmt = con.prepareStatement("SELECT * FROM issueBooks");
        ResultSet rs = pstmt.executeQuery();
        System.out.println("ID | Book ID | Member ID | Issue Date | Return Date");
        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " | " +
                            rs.getInt("bookId") + " | " +
                            rs.getInt("memberId") + " | " +
                            rs.getDate("issueDate") + " | " +
                            rs.getDate("returnDate")
            );
        }
    }
}
