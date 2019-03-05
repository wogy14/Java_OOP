package com.company;

public class StringComp {
    public int compare(String str1,String str2){
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
}
