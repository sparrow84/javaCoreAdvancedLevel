package com.company.lesson06;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {

    private final static String SERVER_ADDR = "localhost";
    private final static int SERVER_PORT = 8189;

    private Socket sock;
    private Scanner in;
    private PrintWriter out;
    private Scanner scanMes;
    private String mes;

    public static void main(String[] args) {
        new ConsoleClient();
    }

    public ConsoleClient() {

        initConnection();

        new Thread(() -> {
            scanMes = new Scanner(System.in);

            while (true) {
                mes = scanMes.nextLine();
                sendMsg(mes);
            }
        }).start();

        new Thread(() -> {
            while (true) {
                String str = in.nextLine();
                if (str.equals("end")) break;
                out.println("Echo: " + str);
                System.out.println("_Echo: " + str);
                out.flush();
            }
        }).start();

    }

    private void initConnection() {
        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(String mes) {
        if (!mes.isEmpty()) {
            out.println(mes);
            out.flush();
            System.out.println(mes);
        }
    }
}
