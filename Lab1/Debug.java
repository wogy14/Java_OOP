package com.company;

public class Debug {
    public static void debug(boolean debug_mode,int length,String arr[],String sorted_arr[]){
        if(debug_mode){
            System.out.println("----Debug Mode----");
            System.out.println("*length"+length);
            System.out.println("*arr:");
            int i = 0;
            for(i = 0;i < length;i++){
                System.out.println(arr[i]);
            }
            System.out.println("*sorted_arr:");
            for(i = 0;i < length;i++){
                System.out.println(sorted_arr[i]);
            }
        }
    }
}
