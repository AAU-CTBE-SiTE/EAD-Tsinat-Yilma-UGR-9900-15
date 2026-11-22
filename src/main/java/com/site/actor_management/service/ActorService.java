package com.site.actor_management.service;

import com.site.actor_management.model.Actor;
import com.site.actor_management.repository.ActorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActorById(int id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Actor with ID " + id + " is not found"));
    }

    public Actor createActor(Actor newActor) {
        newActor.setId(null);
        return actorRepository.save(newActor);
    }

    public Actor updateActor(int id, Actor updatedActor) {
        Actor currentActor = getActorById(id);
        currentActor.setName(updatedActor.getName());
        currentActor.setMovieName(updatedActor.getMovieName());
        return actorRepository.save(currentActor);
    }

    public void deleteActor(int id) {
        Actor currentActor = getActorById(id);
        actorRepository.delete(currentActor);
    }
}
