package UI;

import library.dao.IssueLayer;
import library.models.IssueRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class IssuePage extends JFrame {
    IssueLayer issueLayer = new IssueLayer();

    public IssuePage(){
        setTitle(" Book Issued History ");
        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6,2,10,20));

        JLabel booklbl = new JLabel("BookId");
        JTextField bookField = new JTextField();

        JLabel memberlbl = new JLabel("MemberId");
        JTextField memberField = new JTextField();

        JButton issue = new JButton(" Book Issue");
        JButton returnBook = new JButton(" ReturnBook");
        JButton Show = new JButton("Show Issued Details");
        JButton Back = new JButton(" Back");

        add(booklbl); add(bookField);
        add(memberlbl); add(memberField);
        add(issue); add(returnBook);
        add(Show); add(Back);

        issue.addActionListener(e ->{
            try{
                issueLayer.issueBook(Integer.parseInt(bookField.getText()),Integer.parseInt(memberField.getText()));
            }catch (SQLException ex){
             JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage());
            }
        });

        returnBook.addActionListener(e ->{
            try{
                issueLayer.returnBook(Integer.parseInt(bookField.getText()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage());
            }
        });

        Show.addActionListener(e ->{
            try{
                issueLayer.getIssuedBooks();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro: "+ex.getMessage());
            }
        });

        Back.addActionListener(e -> {
            dispose();
            new OpeningPage();
        });

        setVisible(true);
    }
}
