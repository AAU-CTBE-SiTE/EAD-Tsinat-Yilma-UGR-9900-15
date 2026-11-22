package com.site.actor_management.config;

import com.site.actor_management.model.Actor;
import com.site.actor_management.repository.ActorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ActorDataLoader implements CommandLineRunner {

    private final ActorRepository actorRepository;

    public ActorDataLoader(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public void run(String... args) {
        if (actorRepository.count() > 0) {
            return;
        }
        actorRepository.save(new Actor(null, "Peter Parker", "Spider-man"));
        actorRepository.save(new Actor(null, "Tony Stark", "Iron-man"));
        actorRepository.save(new Actor(null, "Bruce wayne", "Batman"));
        actorRepository.save(new Actor(null, "Logan", "Wolverine"));
        actorRepository.save(new Actor(null, "Steven Strange", "Doctor Strange"));
    }
}
