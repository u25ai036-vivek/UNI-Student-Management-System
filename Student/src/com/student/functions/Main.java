package com.student.functions;

class Student {
    private String name;
    private int age;
    private String major;

    public Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Major: " + major);
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("John", 20, "Computer Science");
        Student student2 = new Student("Alice", 22, "Mathematics");

        student1.displayInfo();
        student2.displayInfo();
    }
}
