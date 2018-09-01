package com.company.lesson04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    public GUI () {
        setTitle("Chat!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        this.setResizable(false);
        setBounds(100,100,300,400);

        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        final JTextField textField = new JTextField(10);

        JButton sendButton = new JButton("Send");
        sendButton.setSize(20,40);

        JPanel messagePanel = new JPanel();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));

        Dimension dim = new Dimension(300,27);

//        messagePanel.setPreferredSize(dim);
//        messagePanel.setMinimumSize(dim);
        messagePanel.setMaximumSize(dim);

        add(textArea);
        add(messagePanel);
        messagePanel.add(textField);
        messagePanel.add(sendButton);

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append(textField.getText() + "\n");
                textField.setText("");
            }
        });

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append(textField.getText() + "\n");
                textField.setText("");
            }
        });

        setVisible(true);
    }
}
