package UI;

import library.dao.MemberLayer;
import library.models.Book;
import library.models.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class MemberPage extends JFrame {
    MemberLayer memberLayer = new MemberLayer();
    public MemberPage(){
        setTitle(" Manage Members ");
        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6,2,10,20));

        JLabel nameLabel = new JLabel("UserName");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email");
        JTextField emailField = new JTextField();

        JLabel Mobile = new JLabel("Phone");
        JTextField phoneField = new JTextField();

        JButton addBtn = new JButton("➕ Add Member");
        JButton updBtn = new JButton(" Update Member");
        JButton delBtn = new JButton(" Delete Member");
        JButton viewBtn = new JButton("👁 View All");
        JButton backBtn = new JButton("⬅ Back");

        add(nameLabel); add(nameField);
        add(emailLabel); add(emailField);
        add(Mobile); add(phoneField);
        add(addBtn); add(viewBtn);
        add(updBtn); add(delBtn);
        add(backBtn);

        addBtn.addActionListener((ActionEvent e) -> {
            try {
                Member member = new Member();
                member.setName(nameField.getText());
                member.setEmail(emailField.getText());
                member.setPhone(Integer.parseInt(phoneField.getText()));
                memberLayer.addMember(member);
                JOptionPane.showMessageDialog(null, "Member added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        viewBtn.addActionListener(e -> {
            try {
                memberLayer.getAllMembers();
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
