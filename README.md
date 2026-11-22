# Lab 8: Student Management API

This project is a REST API for student records. Students are stored in MySQL with Spring Data JPA.

A student has an id, first name, last name, email, and department. MySQL assigns the id when a student is created. A missing id returns 404.

The database password is read from the `DB_PASSWORD` environment variable.

Run it with:

```
$env:DB_PASSWORD="your-mysql-password"
.\mvnw.cmd spring-boot:run
```

Endpoints, all under `/students`:

- `GET /students` lists every student
- `GET /students/{id}` returns one student
- `POST /students` creates a student
- `PUT /students/{id}` updates a student
- `DELETE /students/{id}` deletes a student
