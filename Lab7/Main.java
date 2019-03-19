package com.havryshkiv;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static Vacancy vacFind(int id, List<Vacancy> vac){
        Iterator<Vacancy> myItr = vac.iterator();
        while(myItr.hasNext()){
            Vacancy t = myItr.next();
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        boolean autoMode = false;
        for(int i = 0;i < args.length;i++){
            if(args[i].equals("-auto")){
                autoMode = true;
            }
        }
        List<Vacancy> vacancyList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        if(autoMode){
            try {
                scan = new Scanner(new BufferedInputStream(new FileInputStream("automode.txt")));
            }catch(FileNotFoundException ex){
                System.err.println("Автоматичний режим не запущено!");
                scan = new Scanner(System.in);
            }
        }
        int command,id;
        while(true){
            if(autoMode){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){

                }
            }
            System.out.println("---------------|||---------------");
            System.out.println("1 - додати вакансію");
            System.out.println("2 - видалити вакансію");
            System.out.println("3 - переглянути список вакансій");
            System.out.println("4 - меню вакансії");
            System.out.println("5 - записати вакансії в XML-файл");
            System.out.println("6 - витягти вакансії з XML-файлу");
            System.out.println("7 - записати вакансії в файл(TXT)");
            System.out.println("8 - витягти вакансії з TXT-файлу");
            System.out.println("9 - вийти");
            System.out.println("---------------|||---------------");
            command = scan.nextInt();
            switch(command){
                case 1:
                    System.out.println("Введіть назву фірми:");
                    scan.nextLine();
                    Vacancy vac = new Vacancy();
                    vac.setFirm(scan.nextLine());
                    vacancyList.add(vac);
                    System.out.println("Вакансію '"+vac.getFirm()+"' додано з ідентифікатором: "+vac.getId());
                    break;
                case 2:
                    System.out.println("Введіть ідентифікатор вакансії, щоб видалити:");
                    id = scan.nextInt();
                    Iterator<Vacancy> myItr = vacancyList.iterator();
                    boolean isFound = false;
                    while(myItr.hasNext()){
                        Vacancy t = myItr.next();
                        if(t.getId() == id){
                            myItr.remove();
                            isFound = true;
                            break;
                        }
                    }
                    if(!isFound){
                        System.err.println("Такого елемента не знайдено!");
                    }
                    else{
                        System.out.println("Елемент видалено!");
                    }
                    break;
                case 3:
                    Iterator<Vacancy> myItr1 = vacancyList.iterator();
                    while(myItr1.hasNext()){
                        Vacancy t = myItr1.next();
                        System.out.println(t.toString());
                    }
                    break;
                case 4:
                    System.out.println("Введіть ID вакансії, щоб її редагувати: ");
                    id = scan.nextInt();
                    Vacancy temp = vacFind(id,vacancyList);
                    if(temp != null){
                        System.out.println("Вакансію знайдено!");
                        boolean exit = false;
                        while(!exit) {
                            if(autoMode){
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                }catch(InterruptedException e){

                                }
                            }
                            System.out.println(temp.toString());
                            System.out.println("-------------|||-------------");
                            System.out.println("1 - Змінити фірму");
                            System.out.println("2 - Змінити спеціальність");
                            System.out.println("3 - Змінити умови праці");
                            System.out.println("4 - Змінити оплату");
                            System.out.println("5 - Додати вимогу");
                            System.out.println("6 - Повернутись до гол. меню");
                            System.out.println("-------------|||-------------");
                            command  = scan.nextInt();
                            switch(command){
                                case 1:
                                    System.out.println("Введіть нову фірму:");
                                    scan.nextLine();
                                    temp.setFirm(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 2:
                                    System.out.println("Введіть нову спеціальність:");
                                    scan.nextLine();
                                    temp.setSpeciality(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 3:
                                    System.out.println("Введіть нові умови праці:");
                                    scan.nextLine();
                                    temp.setConditions(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 4:
                                    System.out.println("Введіть нову оплату(у.о.):");
                                    temp.setSalary(scan.nextInt());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 5:
                                    System.out.println("Додавання нових вимог:");
                                    scan.nextLine();
                                    Preference pref = new Preference();
                                    System.out.println("Спеціальність:");
                                    pref.setSpeciality(scan.nextLine());
                                    System.out.println("Стаж:");
                                    pref.setExperience(scan.nextInt());
                                    System.out.println("Освіта:");
                                    scan.nextLine();
                                    pref.setEducation(scan.nextLine());
                                    temp.addPreference(pref);
                                    System.out.println("Успішно додано!");
                                    break;
                                case 6:
                                    exit = true;
                                    break;
                            }
                        }
                    }
                    else{
                        System.out.println("Вакансію не знайдено!");
                    }
                    break;
                case 5: {
                    FileOutputStream fos;
                    System.out.println("----Введіть назву файлу:");
                    scan.nextLine();
                    String file_name = scan.nextLine();
                    System.out.println("----Виберіть потрібну директорію, щоб зберегти файл:");
                    String path = FileManager.selectDir(scan) + "\\" + file_name;
                    if (!(new File(path)).exists()) {
                        File newFile = new File(path);
                        try {
                            if (newFile.createNewFile())
                                System.out.println("***Файл '" + file_name + "' було створено!");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                            break;
                        }
                    }
                    try {
                        fos = new FileOutputStream(path);
                    } catch (FileNotFoundException ex) {
                        System.err.println("Файл не знайдено!");
                        break;
                    }
                    XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(fos));
                    encoder.writeObject(vacancyList.size());
                    for (Vacancy one : vacancyList) {
                        encoder.writeObject(one);
                    }
                    encoder.close();
                    System.out.println("Успішно записано!");
                }
                    break;
                case 6: {
                    System.out.println("1 - Створити новий список(не зберігається попередній)");
                    System.out.println("2 - Додати до поточного списку");
                    command = scan.nextInt();
                    switch (command) {
                        case 1:
                            System.out.println("----Виберіть файл:");
                            scan.nextLine();
                            String path_ = FileManager.selectFile(scan);
                            FileInputStream fis;
                            try {
                                fis = new FileInputStream(path_);
                            } catch (FileNotFoundException ex) {
                                System.err.println("FileNotFound");
                                break;
                            }
                            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
                            Integer size = (Integer) decoder.readObject();
                            vacancyList = new ArrayList<>();
                            Vacancy.cleanVacancy();
                            for (int i = 0; i < size; i++) {
                                vacancyList.add((Vacancy) decoder.readObject());
                            }
                            decoder.close();

                            break;
                        case 2:
                            System.out.println("----Виберіть файл:");
                            scan.nextLine();
                            String path__ = FileManager.selectFile(scan);
                            FileInputStream fis_;
                            try {
                                fis_ = new FileInputStream(path__);
                            } catch (FileNotFoundException ex) {
                                System.err.println("FileNotFound");
                                break;
                            }
                            XMLDecoder decoder_ = new XMLDecoder(new BufferedInputStream(fis_));
                            Integer size_ = (Integer) decoder_.readObject();
                            for (int i = 0; i < size_; i++) {
                                vacancyList.add((Vacancy) decoder_.readObject());
                            }
                            decoder_.close();
                            break;
                    }
                }
                break;
                case 7: {
                    FileOutputStream fos;
                    System.out.println("----Введіть назву файлу(TXT):");
                    scan.nextLine();
                    String file_name = scan.nextLine();
                    System.out.println("----Виберіть потрібну директорію, щоб зберегти файл:");
                    String file = FileManager.selectDir(scan) + "\\" + file_name;
                    if (!(new File(file)).exists()) {
                        File newFile = new File(file);
                        try {
                            if (newFile.createNewFile())
                                System.out.println("New file '" + file_name + "' has been created");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                            break;
                        }
                    }
                    try {
                        fos = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        break;
                    }
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(vacancyList.size());
                        for (Vacancy one : vacancyList) {
                            oos.writeObject(one);
                        }
                        oos.flush();
                        oos.close();
                        fos.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                        break;
                    }
                }
                break;
                case 8:
                {
                    System.out.println("----Виберіть файл:");
                    scan.nextLine();
                    String path = FileManager.selectFile(scan);
                    FileInputStream fis;
                    try {
                        fis = new FileInputStream(path);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found!");
                        break;
                    }
                    try {
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Integer count = (Integer) ois.readObject();
                        for (int i = 0; i < count; i++) {
                            vacancyList.add((Vacancy) ois.readObject());
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage() + "dsds");
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println("Not Found!");
                        break;
                    }
                }break;
                case 9:
                    System.exit(0);
                    break;

            }
        }
    }

}
