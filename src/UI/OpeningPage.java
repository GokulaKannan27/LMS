package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OpeningPage extends JFrame {
    public OpeningPage(){
        setTitle("Library Management System");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4,1,10,10));
        JButton bookBtn = new JButton(" Manage Books");
        JButton memberBtn = new JButton(" Manage Members");
        JButton issueBtn = new JButton(" Issue / Return Books");
        JButton exitBtn = new JButton(" Exit");

        add(bookBtn);
        add(memberBtn);
        add(issueBtn);
        add(exitBtn);

        bookBtn.addActionListener((ActionEvent e) -> new BookPage());
        memberBtn.addActionListener((ActionEvent e) -> new MemberPage());
        issueBtn.addActionListener((ActionEvent e) -> new IssuePage());
        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new OpeningPage();
    }
}
