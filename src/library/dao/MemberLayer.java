package library.dao;

import db.ConnectionClass;
import library.models.Member;

import java.sql.*;

public class MemberLayer {
    Connection con = ConnectionClass.getConnection();
    PreparedStatement pstmt;
    private final String query = "CREATE TABLE IF NOT EXISTS Member("+
            "id INT AUTO_INCREMENT PRIMARY KEY,"+
            "name VARCHAR(50) NOT NULL,"+
            "email VARCHAR(50) NOT NULL,"+
            "phone INT(11) NOT NULL)";
    Statement stmt;
    {
        try{
            stmt =con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Member table created or already exists.");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void addMember(Member member) throws SQLException {
        pstmt = con.prepareStatement("INSERT INTO Member(name,email,phone) VALUES(?,?,?)");
        pstmt.setString(1,member.getName());
        pstmt.setString(2, member.getEmail());
        pstmt.setInt(3, member.getPhone());
        pstmt.executeUpdate();
        System.out.println("Member Added Successfully");
    }
    public void updateMember(Member member) throws  SQLException{
        String sql = "UPDATE Member SET name = ?, email = ?, phone = ? WHERE id = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, member.getName());
        pstmt.setString(2, member.getEmail());
        pstmt.setInt(3, member.getPhone());
        pstmt.setInt(4, member.getId());

        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Members updated successfully!");
        } else {
            System.out.println("No Member found with the given ID.");
        }
    }
    public void deleteMember(int id) throws SQLException{
        pstmt = con.prepareStatement("DELETE FROM Member WHERE id = ?");
        pstmt.setInt(1,id);
        pstmt.executeUpdate();
        System.out.println("Deleted Successfully");
    }
    public void getAllMembers() throws SQLException{
        pstmt = con.prepareStatement("SELECT * FROM Member");
        ResultSet resultSet=pstmt.executeQuery();
        boolean found = false;
        System.out.println("ID |"+" UserName      |"+" Email      |"+" Phone      |");
        while (resultSet.next()){
            System.out.println(
                    resultSet.getInt("id")+" "+resultSet.getString("name")+" "+resultSet.getString("email")+" "+resultSet.getInt("phone")
            );
            found = true;
        }
        if(!found )
            System.out.println("No Member found in table");
    }
}
