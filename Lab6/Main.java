package com.havryshkiv;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main{
    public static void main(String args[]) throws Exception{
        int length = 50000,threads = 4;
        Scanner scan = new Scanner(System.in);
        System.out.println("Введіть бажаний час(мс), після якого програма припинить виконувати свою роботу:");
        (new StopThread(scan.nextInt())).start();
        ArrayList<Future<double[]>> futures = new ArrayList<>();
        ArrayList<double[]> results = new ArrayList<>();
        long start_time = System.currentTimeMillis(),time1,time2;
        ExecutorService exS = Executors.newFixedThreadPool(threads);
        for(int i = 0;i < threads;i++){
            futures.add(exS.submit(new MyCallable(length)));
        }
        exS.shutdown();
        try {
            exS.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        time1 = System.currentTimeMillis() - start_time;
        start_time = System.currentTimeMillis();
        MyCallable thr = new MyCallable(length);
        for(int i = 0;i < threads;i++){
            results.add(thr.call());
        }
        time2 = System.currentTimeMillis() - start_time;
        System.out.println("+---------------+---------------+---------------+---------------+");
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "Тест", "Час виконання","К-сть потоків","К-сть даних");
        System.out.println("+---------------+---------------+---------------+---------------+");
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "#1", time1,threads,length);
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "#2", time2,threads,length);
        System.out.println("+---------------+---------------+---------------+---------------+");
        System.out.println("Результати:");
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+--------------------+");
        System.out.format("|%-15s|%-25s|%-25s|%-25s|%-20s|%n", "Потік", "Min", "Max","Average","Час виконання(мс)");
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+--------------------+");
        for (int i = 0; i < threads; i++) {
            double temp[] = futures.get(i).get();
            System.out.format("|%-15s|%-25s|%-25s|%-25s|%-20s|%n", "Паралельний " + i,temp[1] , temp[2],temp[0],temp[3]);
        }
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+--------------------+");
        for (int i = 0; i < threads; i++) {
            double temp[] = results.get(i);
            System.out.format("|%-15s|%-25s|%-25s|%-25s|%-20s|%n", "Послідовний " + i,temp[1] , temp[2],temp[0],temp[3]);
        }
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+--------------------+");
    }
}
