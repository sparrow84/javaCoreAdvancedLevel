package com.company.lesson07.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientGUI extends JFrame {

    private JTextField jtf;
    private JTextArea jta;

    public ClientGUI(ClientLogic cl) {

        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jta);
        add(jsp, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSend = new JButton("SEND");
        bottomPanel.add(jbSend, BorderLayout.EAST);
        jtf = new JTextField();
        bottomPanel.add(jtf, BorderLayout.CENTER);

        jbSend.addActionListener(e -> {
            if (!jtf.getText().trim().isEmpty()) {
                cl.sendMsg(jtf.getText());
                jtf.setText("");
                jtf.grabFocus();
            }
        });

        jtf.addActionListener(e -> {
            cl.sendMsg(jtf.getText());
            jtf.setText("");
            jtf.grabFocus();
        });

        new Thread(() -> {
            try {
                while (true) {
                    if (cl.in.hasNext()) {
                        String w = cl.in.nextLine();
                        if (w.equalsIgnoreCase("end session")) break;
                        jta.append(w);
                        jta.append("\n");
                    }
                }
            } catch (Exception e) {
                //
            }
//                jta.append(cl.endSession());
//                jta.append("\n");
        }).start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                cl.windowClose();
            }
        });
        setVisible(true);
    }
}