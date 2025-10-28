package library.dao;

import db.ConnectionClass;
import library.models.Book;

import java.sql.*;

public class BookLayer {
        Connection con = ConnectionClass.getConnection();
        PreparedStatement pstmt;
        private final String query = "CREATE TABLE IF NOT EXISTS Books("+
                "id INT AUTO_INCREMENT PRIMARY KEY,"+
                "title VARCHAR(50) NOT NULL,"+
                "author VARCHAR(50) NOT NULL,"+
                "publisher varchar(50) NOT NULL,"+
                "availability varchar(50) NOT NULL)";


       Statement stmt;
        {
                try{
                        stmt =con.createStatement();
                        stmt.executeUpdate(query);
                        System.out.println("Books table created or already exists.");
                }catch (SQLException e){
                        throw new RuntimeException(e);
                }
        }
        public void addBook(Book book) throws SQLException {
            pstmt = con.prepareStatement("INSERT INTO Books(title,author,publisher,availability) VALUES(?,?,?,?)");
            pstmt.setString(1,book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setBoolean(4, book.isAvailable());
            pstmt.executeUpdate();
            System.out.println("Book Added Successfully");
        }
        public void updateBook(Book book) throws SQLException{
            String sql = "UPDATE Books SET title = ?, author = ?, publisher = ?, availability = ? WHERE id = ?";
            pstmt = con.prepareStatement(sql);
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());
                pstmt.setString(3, book.getPublisher());
                pstmt.setBoolean(4, book.isAvailable());
                pstmt.setInt(5, book.getId());

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Book updated successfully!");
                } else {
                    System.out.println("No book found with the given ID.");
                }


        }
        public void deleteBook(int id) throws SQLException{
            pstmt = con.prepareStatement("DELETE FROM Books WHERE id = ?");
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            System.out.println("Deleted Successfully");
        }
        public void getAllBooks() throws SQLException {
            pstmt = con.prepareStatement("SELECT * FROM Books");
            ResultSet resultSet=pstmt.executeQuery();
            boolean found = false;
            System.out.println("ID |"+" Title      |"+" Author       |"+" Publisher      |"+" Available");
            while (resultSet.next()){
                System.out.println(
                        resultSet.getInt("id")+" "+resultSet.getString("title")+" "+resultSet.getString("author")+" "+resultSet.getString("publisher")+" "+(resultSet.getBoolean("availability") ?"Yes":"No")
                );
                found = true;
            }
            if(!found )
                System.out.println("No book found in table");
        }
        public void searchBookByTitle(String title) throws SQLException{
            pstmt = con.prepareStatement("SELECT * FROM Books WHERE title = ?");
            pstmt.setString(1,title);
            ResultSet resultSet=pstmt.executeQuery();
            boolean found = false;
            System.out.println("ID |"+" Title      |"+" Author       |"+" Publisher      |"+" Available");
            while (resultSet.next()){
                System.out.println(
                        resultSet.getInt("id")+" "+resultSet.getString("title")+" "+resultSet.getString("author")+" "+resultSet.getString("publisher")+" "+resultSet.getBoolean("availability")
                );
                found = true;
            }
            if (!found){
                System.out.println("No book found on that title");
            }
        }
}
