package com.company.lesson06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

public class Client extends JFrame {

    private final static String SERVER_ADDR = "localhost";
    private final static int SERVER_PORT = 8189;

    private JTextField jtf;
    private JTextArea jta;
    private Socket sock;
    private Scanner in;
    private PrintWriter out;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {

        initConnection();

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
                sendMsg();
                jtf.grabFocus();
            }
        });
        jtf.addActionListener(e -> sendMsg());
        new Thread(() -> {
            try {
                while (true) {
                    if (in.hasNext()) {
                        String w = in.nextLine();
                        if (w.equalsIgnoreCase("end session")) break;
                        jta.append(w);
                        jta.append("\n");
                    }
                }
            } catch (Exception e) {
                //
            }
        }).start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.println("end");
                    out.flush();
                    sock.close();
                    out.close();
                    in.close();
                } catch (IOException exc) {
                    //
                }
            }
        });

        setVisible(true);
    }

    private void initConnection() {
        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg() {
        out.println(jtf.getText());
        out.flush();
        jtf.setText("");
    }
}
