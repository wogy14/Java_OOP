package com.havryshkiv;

import java.sql.SQLOutput;
import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    public int len;
    public MyCallable(int len){
        this.len = len;
    }
    public double[] call(){
        long start_time = System.currentTimeMillis();
        ListContainer myList = new ListContainer();
        myList.RandomAdd(len);
        double arr[] = myList.minMax();
        double array[] = new double[4];
        array[0] = myList.Average();
        array[1] = arr[0];
        array[2] = arr[1];
        array[3] = System.currentTimeMillis() - start_time;
        return array;
    }
}
