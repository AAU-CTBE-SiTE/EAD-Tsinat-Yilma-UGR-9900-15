package com.craft;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public Address address() {
        Address address = new Address();
        address.setCity("Addis");
        address.setCountry("Ethiopia");
        return address;
    }

    @Bean
    public Subject math() {
        Subject subject = new Subject();
        subject.setSubjectName("Maths 101");
        return subject;
    }

    @Bean
    public Subject java() {
        Subject subject = new Subject();
        subject.setSubjectName("Java EE");
        return subject;
    }

    @Bean
    public Student student() {
        Student student = new Student();
        student.setId(101);
        student.setName("John Doe");
        student.setAge(20);
        student.setAddress(address());
        student.setSubjects(List.of(math(), java()));
        return student;
    }
}