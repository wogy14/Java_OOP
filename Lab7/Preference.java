package com.havryshkiv;

import java.io.Serializable;

public class Preference implements Serializable {
    public String speciality;
    public int experience;
    public String education;

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
    public String toString(){
        return new String("Спеціальність: " + speciality + ", Стаж: " + experience + " Освіта: " + education );
    }
}
