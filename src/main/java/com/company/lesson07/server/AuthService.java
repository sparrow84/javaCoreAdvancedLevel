package com.company.lesson07.server;

import java.util.ArrayList;

public interface AuthService {
    void start();
   String getNickByLoginPass(String login, String pass);
   void stop();
}

