package com.company.lesson02;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

//        String[][] arr = new String[4][4];

        String[][] arr = {
                {"++","02","03","04"},
                {"05","06","07","08"},
                {"09","10","11","12"},
                {"13","14","15","16"}
        };


//        System.out.println(Arrays.toString(arr));
        print2dArray(arr);

        try {
            sumIntArray4by4(arr);
        } catch (MyArraySizeException e) {}
        catch (MyArrayDataException e) {}

    }

    public static void sumIntArray4by4(String[][] arr4b4) throws MyArraySizeException, MyArrayDataException {
        if (arr4b4.length != 4) throw new MyArraySizeException(); // Проверяем кол-во строк
        for (String[] arr: arr4b4) {
            if (arr.length != 4) throw new MyArraySizeException(); // Проверяем кол-во ячеек в строках
        }

        int summ = 0;

        String regex = "\\d+";

        for (int i = 0; i < arr4b4.length; i++) {
            for (int j = 0; j < arr4b4[i].length; j++) {

                if (!arr4b4[i][j].matches(regex)) throw new MyArrayDataException(i,j,arr4b4[i][j]);
                summ += Integer.parseInt(arr4b4[i][j]);
            }
        }

        System.out.println("The sum of the array elements is " + summ);
    }

    static void print2dArray (String[][] arr) {
        for (String[] a: arr) {
            System.out.println(Arrays.toString(a));
        }
    }
}
