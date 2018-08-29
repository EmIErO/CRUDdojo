package com.codecool.crud_dojo;

public class Student {

    private String firstName;
    private String lastName;
    private String age;


    public Student(String firstName, String lastName, String age) {

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

    public void setAge(String age) {
        this.age = age;
    }
}
