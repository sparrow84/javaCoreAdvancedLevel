package com.company.lesson07.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientLogic {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket sock;
    private Scanner in;
    private PrintWriter out;

    public ClientLogic() {
        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String endSession() {

        String w = "";

        try {
            while (true) {
                if (in.hasNext()) {
                    w = in.nextLine();
                    if (w.equalsIgnoreCase("end session")) break;
                }
            }
        } catch (Exception e) {
            //
        }

        return w;
    }

    public void windowClose() {

        System.out.println("---debug--- windowClose");

        try {
            out.println("end");
            out.flush();
            sock.close();
            out.close();
            in.close();
        } catch (IOException exc) {
        }
    }

    public void sendMsg(String msg) {

        System.out.println("---debug--- sendMsg msg = " + msg);

        out.println(msg);
        out.flush();
    }
}
