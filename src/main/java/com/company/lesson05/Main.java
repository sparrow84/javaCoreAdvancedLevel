package com.company.lesson05;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
//        System.out.println("firstM - " + firstM());
        System.out.println("secondM - " + secondM());
        System.out.println("Главный поток завершён...");
    }

    public static long firstM () {

        long tStart;
        long tDelta;
        float[] arr = new float[size];

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        tStart = System.currentTimeMillis();

        calcArray(arr);

        return System.currentTimeMillis() - tStart;
    }

    public static long secondM () {

        long tStart;
        long tDelta;
        float[] arr = new float[size];

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        tStart = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        new Thread(() -> {
            System.out.println("Привет из потока " + Thread.currentThread().getName());
            calcArray(a1);
        }).start();

        new Thread(() -> {
            System.out.println("Привет из потока " + Thread.currentThread().getName());
            calcArray(a2);
        }).start();

//        myT1.start(); // Thread myT1 =
//        myT2.start(); // Thread myT2 =

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        Thread myT3 = new Thread(() -> {
            System.out.println("Привет из потока 3!");
        });

        try {
            myT3.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return System.currentTimeMillis() - tStart;
    }

    public static void calcArray (float[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
