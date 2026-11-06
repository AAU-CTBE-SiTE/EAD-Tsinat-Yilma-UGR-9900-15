package com.craft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // Spring reads beansConfig.xml and creates/configures the beans.
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beansConfig.xml");

        // Ask the IoC container for the Student bean; do not use new Student().
        Student student = context.getBean("student", Student.class);

        student.getDetails();

        // Close the context so resources can be released.
        ((ClassPathXmlApplicationContext) context).close();
    }
}