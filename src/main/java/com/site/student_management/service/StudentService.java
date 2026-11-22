package com.site.student_management.service;

import com.site.student_management.model.Student;
import com.site.student_management.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student with ID " + id + " is not found"));
    }

    public Student createStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student currentStudent = getStudentById(id);
        currentStudent.setFirstName(updatedStudent.getFirstName());
        currentStudent.setLastName(updatedStudent.getLastName());
        currentStudent.setEmail(updatedStudent.getEmail());
        currentStudent.setDepartment(updatedStudent.getDepartment());
        return studentRepository.save(currentStudent);
    }

    public void deleteStudent(Long id) {
        Student currentStudent = getStudentById(id);
        studentRepository.delete(currentStudent);
    }
}
