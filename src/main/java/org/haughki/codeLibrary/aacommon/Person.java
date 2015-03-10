package org.haughki.codeLibrary.aacommon;

import java.time.LocalDate;

public class Person {

    public Person(String name, byte age, Sex gender, String emailAddress) {
        this.name = name;
        this.birthday = LocalDate.MIN;
        this.age = age;
        this.gender = gender;
        this.emailAddress = emailAddress;
    }

    public enum Sex {
        MALE, FEMALE
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Sex getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    String name;
    LocalDate birthday;
    byte age;
    Sex gender;
    String emailAddress;

    public int getAge() {
        return this.age;
    }

    public void printPerson() {
        System.out.println(String.format("%s  %s  %d", this.name, this.emailAddress, getAge()));
    }
}