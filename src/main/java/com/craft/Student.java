package com.craft;

public class Student {
    private int id;
    private String name;
    private int age;
    private Address address;

    // No-argument constructor used by setter-injection examples.
    public Student() {
    }

    // Parameterized constructor used by constructor-injection examples.
    public Student(int id, String name, int age, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void getDetails() {
        System.out.println("Student Details: " + this);
    }

    @Override
    public String toString() {
        return "Student{id=" + id
                + ", name='" + name + '\''
                + ", age=" + age
                + ", address=" + address + '}';
    }
}