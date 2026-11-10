package com.example.api_demo.model;

public class Actor {
    private int id;
    private String name;
    private String movieName; // This holds the country name for your presidents

    // Default Constructor (Required by Spring Boot to read JSON data)
    public Actor() {
    }

    // Constructor with parameters to create the initial list
    public Actor(int id, String name, String movieName) {
        this.id = id;
        this.name = name;
        this.movieName = movieName;
    }

    // Getters and Setters
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

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    // The custom output method used by your controller's "/iterate" endpoint
    public String output() {
        return "ID: " + this.id + " | Leader: " + this.name + " | Country: " + this.movieName;
    }
}
