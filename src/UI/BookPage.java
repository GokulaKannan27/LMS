package UI;

import library.dao.BookLayer;
import library.models.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class BookPage extends JFrame {
    BookLayer bookLayer = new BookLayer();

    public BookPage(){
        setTitle("📚 Manage Books");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel titleLbl = new JLabel("Title:");
        JTextField titleField = new JTextField();

        JLabel authorLbl = new JLabel("Author:");
        JTextField authorField = new JTextField();

        JLabel pubLbl = new JLabel("Publisher:");
        JTextField pubField = new JTextField();

        JLabel availability = new JLabel("Available(True or False)");
        JTextField avail = new JTextField();

        JButton addBtn = new JButton("➕ Add Book");
        JButton updBtn = new JButton(" Update Book");
        JButton delBtn = new JButton(" Delete Book");
        JButton viewBtn = new JButton("👁 View All");
        JButton backBtn = new JButton("⬅ Back");

        add(titleLbl); add(titleField);
        add(authorLbl); add(authorField);
        add(pubLbl); add(pubField);
        add(availability); add(avail);
        add(addBtn); add(viewBtn);
        add(updBtn); add(delBtn);
        add(backBtn);

        addBtn.addActionListener((ActionEvent e) -> {
            try {
                Book book = new Book();
                book.setTitle(titleField.getText());
                book.setAuthor(authorField.getText());
                book.setPublisher(pubField.getText());
                book.setAvailable(Boolean.parseBoolean(avail.getText()));
                bookLayer.addBook(book);
                JOptionPane.showMessageDialog(null, "Book added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

       /* updBtn.addActionListener(e ->{
            try{
                Book book = new Book();
                book.setId();
                book.setTitle(titleField.getText());
                book.setAuthor(authorField.getText());
                book.setPublisher(pubField.getText());
                book.setAvailable(Boolean.parseBoolean(avail.getText()));
                bookLayer.updateBook();
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
            }
        });*/


        viewBtn.addActionListener(e -> {
            try {
                bookLayer.getAllBooks();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


        backBtn.addActionListener(e -> {
            dispose();
            new OpeningPage();
        });

        setVisible(true);
    }
}