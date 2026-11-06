package com.craft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterObjectDemo {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beansConfig-object-setter.xml");

        Student student = context.getBean("student", Student.class);
        student.getDetails();

        ((ClassPathXmlApplicationContext) context).close();
    }
}