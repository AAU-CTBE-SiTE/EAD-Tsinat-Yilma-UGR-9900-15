package com.site.actor_management.controller;

import com.site.actor_management.aspect.LogExecutionTime;
import com.site.actor_management.model.Actor;
import com.site.actor_management.service.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Actor")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @LogExecutionTime
    @PostMapping
    public Actor createActor(@RequestBody Actor newActor) {
        return actorService.createActor(newActor);
    }

    @LogExecutionTime
    @GetMapping
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @LogExecutionTime
    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable int id) {
        return actorService.getActorById(id);
    }

    @LogExecutionTime
    @GetMapping(value = "/iterate", produces = "text/plain")
    public String iterateAllActors() {
        StringBuilder result = new StringBuilder();
        for (Actor currentActor : actorService.getAllActors()) {
            result.append(currentActor.output()).append("\n");
        }
        return result.toString();
    }

    @LogExecutionTime
    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable int id, @RequestBody Actor updatedActor) {
        return actorService.updateActor(id, updatedActor);
    }

    @LogExecutionTime
    @DeleteMapping("/{id}")
    public String deleteActor(@PathVariable int id) {
        actorService.deleteActor(id);
        return "Actor with ID " + id + " is deleted";
    }
}
