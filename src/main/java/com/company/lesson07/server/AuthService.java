package com.company.lesson07.server;

public interface AuthService {
    void start();
   String getNickByLoginPass(String login, String pass);
   void stop();
}

