package com.site.actor_management.controller;

import com.site.actor_management.aspect.LogExecutionTime;
import com.site.actor_management.model.Actor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    private Actor findActor(int id) {
        for (Actor currentActor : actors) {
            if (currentActor.getId() == id) {
                return currentActor;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor with ID " + id + " is not found");
    }

    @LogExecutionTime
    @PostMapping
    public Actor createActor(@RequestBody Actor newActor) {
        actors.add(newActor);
        return newActor;
    }

    @LogExecutionTime
    @GetMapping
    public List<Actor> getAllActors() {
        return actors;
    }

    @LogExecutionTime
    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable int id) {
        return findActor(id);
    }

    @LogExecutionTime
    @GetMapping(value = "/iterate", produces = "text/plain")
    public String iterateAllActors() {
        StringBuilder result = new StringBuilder();
        for (Actor currentActor : actors) {
            result.append(currentActor.output()).append("\n");
        }
        return result.toString();
    }

    @LogExecutionTime
    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable int id, @RequestBody Actor updatedActor) {
        Actor currentActor = findActor(id);
        currentActor.setName(updatedActor.getName());
        currentActor.setMovieName(updatedActor.getMovieName());
        return currentActor;
    }

    @LogExecutionTime
    @DeleteMapping("/{id}")
    public String deleteActor(@PathVariable int id) {
        Actor currentActor = findActor(id);
        actors.remove(currentActor);
        return "Actor with ID " + id + " is deleted";
    }
}
