# Lab 7: Spring Data JPA

This project is the actor API saved in MySQL with Spring Data JPA.

The same endpoints from the earlier lab are still here. Actors are stored in the `actors` table in the `actor_db` database instead of a list in memory. A missing id still returns 404. The five sample actors are inserted only when the table is empty. MySQL assigns the id on create.

The database password is read from the `DB_PASSWORD` environment variable.

Run it with:

```
$env:DB_PASSWORD="your-mysql-password"
.\mvnw.cmd spring-boot:run
```

Endpoints, all under `/Actor`:

- `GET /Actor` lists every actor
- `GET /Actor/{id}` returns one actor
- `GET /Actor/iterate` prints every actor as plain text
- `POST /Actor` creates an actor
- `PUT /Actor/{id}` updates the name and movie
- `DELETE /Actor/{id}` deletes an actor
