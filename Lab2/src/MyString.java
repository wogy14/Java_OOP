package com.tasks5.command;

import java.io.*;
import java.util.Iterator;

public class MyString implements Serializable,Iterable<String> {
    private String[] words;
    private int length;
    private static final long serialVersionUID = -5596516260773188077L;

    public MyString(int len){
        if(len > 0){
            words = new String[len];
        }
        else{
            throw new ExceptionInInitializerError();
        }
    }
    public String toString(){
        return toString(", ");
    }
    public String toString(String devid){
        if(length == 0){
            return null;
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0;i < length;i++){
            str.append(words[i]).append(devid);
        }
        return str.toString();
    }
    public void add(String string){
        if(string != null){
            words[length++] = string;
        }
        else{
            throw new NullPointerException();
        }
    }
    public void clear(){
        for(int i = 0;i < length;i++){
            words[i] = null;
        }
        length = 0;
    }
    public boolean remove(String string){
        boolean checker = false;
        for(int i = 0; i < length;i++){
            if(checker){
                words[i-1] = words[i];
            }
            else if(string.equals(words[i])){
                checker = true;
            }
        }
        if(checker){
            length--;
            return true;
        }
        else return false;
    }
    public boolean remove(int id){
        boolean checker = false;
        for(int i = 0; i < length;i++){
            if(checker){
                words[i-1] = words[i];
            }
            else if(this.get(id) != null){
                checker = true;
            }
        }
        if(checker){
            length--;
            return true;
        }
        else return false;
    }
    public String[] toArray(){
        String[] arr = new String[length];
        System.arraycopy(words, 0, arr, 0, length);
        return arr;
    }
    public int size(){
        return length;
    }
    public boolean contains(String str){
        for(int i = 0; i < length;i++){
            if(words[i].equals(str)){
                return true;
            }
        }
        return false;
    }
    public boolean containsAll(MyString cont){
        boolean checker;
        for(int i = 0;i < length;i++){
            checker = false;
            for(int j = 0;j < cont.size();j++){
                if(words[i].equals(cont.get(j))){
                    checker = true;
                    break;
                }
            }
            if(!checker){
                return false;
            }
        }
        return true;
    }
    public String get(int id) {
        if (id >= length)
            return null;
        else
            return words[id];
    }
    public int get(String str) {
        for(int i = 0;i < length;i++){
            if(words[i].equals(str)){
                return i;
            }
        }
        return -1;
    }
    public boolean serialize(String file){
        FileOutputStream fos;
        if (!(new File(file)).exists()) {
            File newFile = new File(file);
            try
            {
                if(newFile.createNewFile())
                    System.out.println("New file '"+file+"' has been created");
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            fos.close();
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean deserialize(String filename) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return false;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            MyString t = (MyString) ois.readObject();
            if (words.length > this.words.length) {
                System.out.println("Size of container is too large!");
                return false;
            }
            this.words = t.words;
            this.length = t.length;
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + "dsds");
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Not Found!");
            return false;
        }
    }
    public int compare(int id1,int id2){
        String str1 = words[id1];
        String str2 = words[id2];
        if(str1.length() != str2.length()){
            return str1.length() - str2.length();
        }
        for(int i = 0;i<str1.length();i++){
            if(str1.charAt(i) != str2.charAt(i)){
                return str1.charAt(i) - str2.charAt(i);
            }
        }
        return 0;
    }
    public void sort(){
        for(int i=0;i<length-1;i++){
            for(int j=i+1;j<length;j++){
                if(this.compare(i,j) > 0){
                    String tmp=words[i];
                    words[i]=words[j];
                    words[j]=tmp;
                }
            }
        }
    }
    public int max_size(){
        return words.length;
    }
    public Iterator<String> iterator() {
        return new NewIterator(this, length);
    }
}