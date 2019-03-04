package com.company;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections ;

public class Main {

    public static void main(String[] args) {
        String s;
        ArrayList<String> arr = new ArrayList<String>();
        System.out.println("Введіть рядки(напишіть \\stop щоб зупинитись):");
        Scanner scan = new Scanner(System.in);
        s = scan.nextLine();
        while(!s.equals("\\stop")){
            arr.add(s);
            s = scan.nextLine();
        }
        Collections.sort(arr);
        for(int i = 0;i < arr.size();i++){
            System.out.println((i+1)+". "+arr.get(i));
        }
    }
}
