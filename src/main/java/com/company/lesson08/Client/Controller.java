package com.company.lesson08.Client;

public interface Controller {
    void sendMessage(String msg);

    void closeConnection();

    void showUI(ClientUI clientUI);
}
