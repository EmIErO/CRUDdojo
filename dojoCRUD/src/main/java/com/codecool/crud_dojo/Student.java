package com.codecool.crud_dojo;

public class Student {

    private int ID = 0;
    private String firstName;
    private String lastName;
    private int age;


    public Student(String firstName, String lastName, Integer age) {
        this.ID = ++ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
