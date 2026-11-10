package com.example.api_demo.controller;

import com.example.api_demo.model.Actor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Actor")
public class ActorController {

    private List<Actor> actors = new ArrayList<>();

    public ActorController() {
        actors.add(new Actor(1, "Peter Parker", "Spider-man"));
        actors.add(new Actor(2, "Tony Stark", "Iron-man"));
        actors.add(new Actor(3, "Bruce wayne", "Batman"));
        actors.add(new Actor(4, "Logan", "Wolverine"));
        actors.add(new Actor(5, "Steven Strange", "Doctor Strange"));
    }

    @PostMapping
    public Actor createActor(@RequestBody Actor newActor) {
        actors.add(newActor);
        return newActor;
    }

    @GetMapping
    public List<Actor> getAllActors() {
        return actors;
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable int id) {
        for (Actor currentActor : actors) {
            if (currentActor.getId() == id) {
                return currentActor;
            }
        }
        return null;
    }

    @GetMapping(value = "/iterate", produces = "text/plain")
    public String iterateAllActors() {
        StringBuilder result = new StringBuilder();
        for (Actor currentActor : actors) {
            result.append(currentActor.output()).append("\n");
        }
        return result.toString();
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable int id, @RequestBody Actor updatedActor) {
        for (Actor currentActor : actors) {
            if (currentActor.getId() == id) {
                currentActor.setName(updatedActor.getName());
                currentActor.setMovieName(updatedActor.getMovieName());
                return currentActor;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteActor(@PathVariable int id) {
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).getId() == id) {
                actors.remove(i);
                return "Actor with ID " + id + " is deleted";
            }
        }
        return "Actor with ID " + id + " is not found";
    }
}
