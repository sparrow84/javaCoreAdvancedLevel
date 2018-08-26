package com.company.lesson02;

public class MyArraySizeException extends Exception {

    public MyArraySizeException() {
        System.err.println("MyArraySizeException: Dimension of the array should be 4 by 4.");
    }
}
