package com.havryshkiv;
import javafx.util.Pair;

import java.io.File;
import java.util.*;

public class FileManager {

    public static String selectDir(Scanner scan){
        boolean end = false;
        String path = "D:\\";
        String choice;
        while(!end){
            File dir = new File(path);
            if(dir.isDirectory()){
                for(File item : dir.listFiles()){
                    if(item.isDirectory()){
                        System.out.println(item.getName());
                    }
                }
            }
            else{
                System.out.println("Такої директорії не існує! Виберіть ще раз!");
            }
            System.out.println("Зробіть свій вибір. Якщо ви знаходитесь в потрібній папці, то напишіть '/stop'");
            System.out.println("'/prev' - повернутись назад.");
            choice = scan.nextLine();
            if(choice.equals("/stop")){
                return path;
            }
            if(choice.equals("/prev")){
                path = dir.getParent();
            }
            else {
                path += "\\" + choice;
            }

        }
        return null;
    }
    public static String selectFile(Scanner scan){
        String path = "D:\\";
        String choice;
        while(true){
            File dir = new File(path);
            if(dir.isDirectory()){
                for(File item : dir.listFiles()){
                    if(item.isDirectory()){
                        System.out.println(item.getName() + "\t Dir");
                    }
                    else{
                        System.out.println(item.getName() + "\t File");
                    }
                }
            }
            else if(dir.isFile()){
                return path ;
            }
            else{
                System.err.println("Такої директорії або файлу не існує!");
            }
            System.out.println("Зробіть свій вибір.");
            System.out.println("'/prev' - повернутись назад.");
            choice = scan.nextLine();
            if(choice.equals("/prev")){
                path = dir.getParent();
            }
            else {
                path += "\\" + choice;
            }
        }
    }
}
