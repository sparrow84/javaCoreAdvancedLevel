package com.company.lesson07.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
   private Socket socket;
   private DataInputStream in;
   private DataOutputStream out;
   private String name;
   public String getName() {
       return name;
   }
   public ClientHandler(Server Server, Socket socket) {
       try {
           this.server = Server;
           this.socket = socket;
           this.in = new DataInputStream(socket.getInputStream());
           this.out = new DataOutputStream(socket.getOutputStream());
           this.name = "";
           new Thread(() -> {
               try {
                   while (true) { // цикл авторизации
                       String str = in.readUTF();
                       if (str.startsWith("/auth")) {
                           String[] parts = str.split("\\s");
                           String nick = Server.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                           if (nick != null) {
                               if (!Server.isNickBusy(nick)) {
                                   sendMsg("/authok " + nick);
                                   name = nick;
                                   Server.broadcastMsg(name + " зашел в чат");
                                   Server.subscribe(this);
                                   break;
                               } else sendMsg("Учетная запись уже используется");
                           } else {
                               sendMsg("Неверные логин/пароль");
                           }
                       }
                   }
                   while (true) { // цикл получения сообщений
                       String str = in.readUTF();
                       System.out.println("от " + name + ": " + str);
                       if (str.equals("/end")) break;
                       Server.broadcastMsg(name + ": " + str);
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               } finally {
                   Server.unsubscribe(this);
                   Server.broadcastMsg(name + " вышел из чата");
                   try {
                       socket.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }).start();
       } catch (IOException e) {
           throw new RuntimeException("Проблемы при создании обработчика клиента");
       }
   }
   public void sendMsg(String msg) {
       try {
           out.writeUTF(msg);
           System.out.println("sendMsg: " + msg);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

}
