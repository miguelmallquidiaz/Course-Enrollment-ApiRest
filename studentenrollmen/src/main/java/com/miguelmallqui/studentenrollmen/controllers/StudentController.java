package com.miguelmallqui.studentenrollmen.controllers;

import com.miguelmallqui.studentenrollmen.models.Student;
import com.miguelmallqui.studentenrollmen.repository.StudentRepository;
import com.miguelmallqui.studentenrollmen.services.StudentService;
import com.miguelmallqui.studentenrollmen.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "*") //para cargar los datos de la bd en el front
@RestController
public class StudentController {
    Validators emailValidator = new Validators();

    @Autowired
    StudentRepository repositorioDeEstudiantes;

    @PostMapping("/api/student")
    public String registerStudent(@RequestBody Student student){
        String studentMail = student.getEmail();
        String studentName = student.getName();
        String studentPhone = student.getPhone();
        if(emailValidator.validateEmail(studentMail)==false){
            return "The email address you entered is not valid";
        }
        repositorioDeEstudiantes.addStudent(studentName, studentMail, studentPhone);
        return "Student added to database.";
    }

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PutMapping("/api/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        try {
            Student updated = studentService.updateStudent(id, updatedStudent.getName(), updatedStudent.getEmail(), updatedStudent.getPhone());
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("Student not found"));
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/api/student")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> getAll = studentService.getAllStudents();
            return ResponseEntity.ok(getAll);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/student/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
