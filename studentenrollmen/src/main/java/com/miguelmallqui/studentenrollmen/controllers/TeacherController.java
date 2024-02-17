package com.miguelmallqui.studentenrollmen.controllers;
import com.miguelmallqui.studentenrollmen.models.Teacher;
import com.miguelmallqui.studentenrollmen.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "*") //para cargar los datos de la bd en el front
@RestController
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/api/teacher")
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher professor){
        try {
            Teacher newTeacher = teacherService.saveTeacher(professor);
            return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
        } catch (NoSuchElementException e){
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/teacher")
    public ResponseEntity<List<Teacher>> getAllTeacher(){
        try {
            List<Teacher> getAllTeacher = teacherService.getAllTeacher();
            return ResponseEntity.ok(getAllTeacher);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/teacher/{id}")
    public ResponseEntity<Teacher> getIdTeacher(@PathVariable Long id) {
        try {
            Teacher getIdTeacher = teacherService.getIdTeacher(id);
            return ResponseEntity.ok(getIdTeacher);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/teacher/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        try {
            teacherService.deleteTeacherById(id);
            return ResponseEntity.ok("Teacher desactivated successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/teacher/update/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        try {
            Teacher updateteacher = teacherService.updateProfessor(
                    id,
                    teacher.getName(),
                    teacher.getEmail(),
                    teacher.getPhone()
            );
            return ResponseEntity.ok(updateteacher);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
