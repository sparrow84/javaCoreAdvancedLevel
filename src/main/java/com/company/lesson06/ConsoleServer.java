package com.company.lesson06;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleServer {

    private final static int SERVER_PORT = 8189;

    private ServerSocket serv = null;
    private Socket sock;
    private Scanner sc;
    private PrintWriter pw;
    private Scanner scanMes;

    public static void main(String[] args) {
        new ConsoleServer();
    }

    public ConsoleServer() {

//        new Thread(() -> {});

        initConnection();

        new Thread(() -> {
            while (true) {
                String str = sc.nextLine();
                if (str.equals("end")) break;
                pw.println("Echo: " + str);
                System.out.println("_Echo: " + str);
                pw.flush();
            }
        }).start();

        new Thread(() -> {
            scanMes = new Scanner(System.in);

            while (true) {
                sendMsg(scanMes.nextLine());
            }
        }).start();

    }

    private void initConnection() {
        try {
            serv = new ServerSocket(SERVER_PORT);
            System.out.println("Server started, waiting for connection ...");
            sock = serv.accept();
            System.out.println("A client is connected");
            sc = new Scanner(sock.getInputStream());
            pw = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println("Server initialization error");
        } finally {
            try {
                serv.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void sendMsg(String mes) {
        if (!mes.isEmpty()) {
            pw.println(mes);
            pw.flush();
            System.out.println(mes);
        }
    }
}
