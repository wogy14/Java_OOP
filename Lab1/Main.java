package com.company;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        boolean debug_mode = false;
        if(args != null){
            if(args[0].equals("-debug") || args[0].equals("-d")){
                debug_mode = true;
            }
            if(args[0].equals("-help") || args[0].equals("-h")){
                Help.help();
            }
        }
        String[] arr = new String[100];
        String[] sorted_arr = new String[100];
        int a,length = 0;
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Виберіть дію, яку слід виконати:");
            System.out.println("1 - Ввести рядки");
            System.out.println("2 - Переглянути дані");
            System.out.println("3 - Посортувати");
            System.out.println("4 - Переглянути результат");
            System.out.println("5 - Завершити роботу");
            a = scan.nextInt();
            int i = 0;
            switch(a){
                case 1:
                    Debug.debug(debug_mode,length,arr,sorted_arr);
                    String s;
                    s = scan.nextLine();
                    System.out.println("Введіть рядки(напишіть /stop щоб зупинитись):");
                    while(!s.equals("/stop")){
                        arr[i] = s;
                        sorted_arr[i] = s;
                        s = scan.nextLine();
                        i++;
                    }
                    length = i;
                    Debug.debug(debug_mode,length,arr,sorted_arr);
                    break;
                case 2:
                    Debug.debug(debug_mode,length,arr,sorted_arr);
                    for(i = 0;i < length;i++){
                        System.out.println(arr[i]);
                    }
                    Debug.debug(debug_mode,length,arr,sorted_arr);
                    break;
                case 3:
                    Debug.debug(debug_mode,length,arr,sorted_arr);
                    StringComp comp = new StringComp();
                    for(i=0;i<length-1;i++){
                        for(int j=i+1;j<length;j++){
                            if(comp.compare(sorted_arr[i],sorted_arr[j]) > 0){
                                String tmp=sorted_arr[i];
                                sorted_arr[i]=sorted_arr[j];
                                sorted_arr[j]=tmp;
                            }
                        }
                    }
                    Debug.debug(debug_mode,length,arr,sorted_arr);
                    break;
                case 4:
                    Debug.debug(debug_mode,length,arr,sorted_arr);
                    for(i = 0;i < length;i++){
                        System.out.println(sorted_arr[i]);
                    }
                    Debug.debug(debug_mode,length,arr,sorted_arr);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    Debug.debug(debug_mode,length,arr,sorted_arr);
                    System.out.println("Такої команди не існує!");
                    break;
            }
        }
    }
}
