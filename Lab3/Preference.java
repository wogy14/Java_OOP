package com.havryshkiv;

public class Preference {
    private String speciality;
    private int experience;
    private String education;

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
