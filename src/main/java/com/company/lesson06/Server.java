package com.company.lesson06;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock;

        try {
            serv = new ServerSocket(8189);
            System.out.println("Server started, waiting for connection ...");
            sock = serv.accept();
            System.out.println("A client is connected");
            Scanner sc = new Scanner(sock.getInputStream());
            PrintWriter pw = new PrintWriter(sock.getOutputStream());

            while (true) {
                String str = sc.nextLine();
                if (str.equals("end")) break;
                pw.println("Echo: " + str);
                System.out.println("Echo: " + str);
                pw.flush();
            }
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
}
