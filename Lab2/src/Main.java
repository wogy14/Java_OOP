package com.tasks5.command;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int command;
        MyString  container = null;
        MyString cont2 = new MyString(1000);
        cont2.add("abs");
        cont2.add("abs");
        cont2.add("abs");
        cont2.add("abs");
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("---My menu---");
            System.out.println("1 - make container");
            System.out.println("2 - fill container");
            System.out.println("3 - clear container");
            System.out.println("4 - show container");
            System.out.println("5 - container menu");
            System.out.println("6 - exit");
            command = scan.nextInt();
            switch(command){
                case 1:
                    System.out.println("Введіть довжину контейнера");
                    container = new MyString(scan.nextInt());
                    System.out.println("Контейнер створено.");
                    break;
                case 2:
                    if(container != null) {
                        String s;
                        scan.nextLine();
                        System.out.println("Введіть рядки. Напишіть '/stop',щоб зупинитись.");
                        s = scan.nextLine();
                        while (!s.equals("/stop")) {
                            if (s.length() <= 0) {
                                System.out.println("Введіть не пусту стрічку. Попробуйте ще раз!");
                            } else {
                                container.add(s);
                            }
                            s = scan.nextLine();
                        }
                    }
                    else{
                        System.out.println("Контейнер не створений!");
                    }
                    break;
                case 3:
                    if(container != null){
                        container.clear();
                        System.out.println("Контейнер почищено!");
                    }
                    else{
                        System.out.println("Контейнер не створений!");
                    }
                    break;
                case 4:
                    if(container != null){
                        System.out.println(container.toString());
                    }
                    else{
                        System.out.println("Контейнер не створений!");
                    }
                    break;
                case 5:
                    if(container == null){
                        System.out.println("Контейнер не створений!");
                        break;
                    }
                    System.out.println("1 - add element");
                    System.out.println("2 - remove element");
                    System.out.println("3 - convert to array and iterate through");
                    System.out.println("4 - current size");
                    System.out.println("5 - max size");
                    System.out.println("6 - check string");
                    System.out.println("7 - write to file (serialize)");
                    System.out.println("8 - read from file (deserialize)");
                    System.out.println("9 - get element by index");
                    System.out.println("10 - get element's index");
                    System.out.println("11 - sort");
                    System.out.println("12 - iterate through container (foreach)");
                    System.out.println("13 - iterate through container (while)");
                    System.out.println("14 - return");
                    command = scan.nextInt();
                    switch(command){
                        case 1:
                            scan.nextLine();
                            System.out.println("Введіть рядкок, щоб додати:");
                            container.add(scan.nextLine());
                            break;
                        case 2:
                            scan.nextLine();
                            System.out.println("Введіть рядкок, для видалення:");
                            if(container.remove(scan.nextLine())){
                                System.out.println("Рядок видалено!");
                            }
                            else{
                                System.out.println("Такого рядка не знайдено!");
                            }
                            break;
                        case 3:
                            String arr[] = new String[container.size()];
                            arr = container.toArray();
                            for(String s : arr){
                                System.out.print(s + ",");
                            }
                            break;
                        case 4:
                            System.out.println("Розмір: " + container.size());
                            break;
                        case 5:
                            System.out.println("Розмір: " + container.max_size());
                            break;
                        case 6:
                            scan.nextLine();
                            System.out.println("Введіть рядок, який потрібно перевірити:");
                            if(container.contains(scan.nextLine())){
                                System.out.println("Такий елемент знайдено!");
                            }
                            else{
                                System.out.println("Немає такого елемента!");
                            }
                            break;
                        case 7:
                            scan.nextLine();
                            System.out.println("Введіть назву файлу для запису:");
                            if(container.serialize(scan.nextLine())){
                                System.out.println("Записано!");
                            }
                            else{
                                System.out.println("Не вдалий запис!");
                            }
                            break;
                        case 8:
                            scan.nextLine();
                            System.out.println("Введіть назву файлу, щоб зчитати:");
                            if(container.deserialize(scan.nextLine())){
                                System.out.println("Дані отримано!");
                            }
                            else{
                                System.out.println("Неможливо отримати дані!");
                            }
                            break;
                        case 9:
                            System.out.println("Введіть індекс:");
                            int s = scan.nextInt();
                            String str = container.get(s);
                            if(str != null){
                                System.out.println("Ось рядок: " + str);
                            }
                            else{
                                System.out.println("Не має такого рядка!");
                            }
                            break;
                        case 10:
                            scan.nextLine();
                            System.out.println("Введіть рядок:");
                            String str1 = scan.nextLine();
                            int id = container.get(str1);
                            if(id != -1){
                                System.out.println("Ось індекс: " + id);
                            }
                            else{
                                System.out.println("Немає такого рядка!");
                            }
                            break;
                        case 11:
                            container.sort();
                            System.out.println("Посортовано!");
                            break;
                        case 12:
                            for(String s1 : container.toArray()){
                                System.out.println(s1+ ", ");
                            }
                            break;
                        case 13:
                            Iterator<String> iter = container.iterator();
                            while(iter.hasNext()){
                                System.out.println(iter.next() + ", ");
                            }
                            break;
                        case 14:
                            break;
                    }

                    break;
                case 6:
                    System.exit(0);
                    break;
                case 7:
                    System.out.println(container.containsAll(cont2));

            }
        }
    }
}
