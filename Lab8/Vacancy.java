package com.havryshkiv;

import java.io.Serializable;
import java.util.ArrayList;

public class Vacancy implements Serializable {
    private static int counter = 0;
    private int id;
    private String firm;
    private String speciality;
    private String conditions;
    private Integer salary;
    public ArrayList<Preference> preferences;

    public Vacancy(){
        preferences = new ArrayList<>();
        id = counter++;
    }
    public Vacancy(String firm){
        preferences = new ArrayList<>();
        id = counter++;
        this.firm = firm;
    }
    public static void cleanVacancy(){
        counter = 0;
    }
    public void addPreference(Preference pref){
        preferences.add(pref);
    }
    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("-----------------------------------------\n");
        str.append("ID: ").append(id).append("\n");
        str.append("Фірма: ").append(firm).append("\n");
        str.append("Спеціальність: ").append(speciality).append("\n");
        str.append("Умови праці: ").append(conditions).append("\n");
        str.append("Оплата: ").append(salary).append("у.о.\n");
        str.append("Вимоги:\n");
        int count = 0;
        for(Preference pref : preferences){
            str.append("\t").append(count).append(". ").append(pref.toString()).append("\n");
        }
        str.append("-----------------------------------------");
        return str.toString();
    }
}
