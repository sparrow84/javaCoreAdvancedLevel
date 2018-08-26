package com.company.lesson02;

public class MyArrayDataException extends Exception {
    public MyArrayDataException() {
        System.err.println("MyArrayDataException");
    }

    public MyArrayDataException(int i, int j, String s) {
        System.err.println("MyArrayDataException: in cell [" + i + "," + j + "] not a number (" + s + ")");
    }
}
