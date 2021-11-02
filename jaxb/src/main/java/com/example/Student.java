package com.example;

import javax.xml.bind.annotation.*;


public class Student {


    @XmlElement(name = "lastname")
    String lastName;

    @XmlAttribute(name = "mark")
    Double averageMark;

    @XmlAttribute(name = "age")
    Integer age;


    public Student() {
    }

    public Student(String lastName,Double averageMark,Integer age) {
        this.lastName = lastName;
        this.averageMark = averageMark;
        this.age = age;
    }

    public void print(){


        System.out.println(this.lastName +" "+ this.averageMark +" "+ this.age);


    }
    
}
