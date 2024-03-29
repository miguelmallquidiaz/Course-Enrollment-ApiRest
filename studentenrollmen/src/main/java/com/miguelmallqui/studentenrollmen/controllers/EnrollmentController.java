package com.miguelmallqui.studentenrollmen.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miguelmallqui.studentenrollmen.models.Course;
import com.miguelmallqui.studentenrollmen.models.Enrollment;
import com.miguelmallqui.studentenrollmen.models.Student;
import com.miguelmallqui.studentenrollmen.services.EnrollmentService;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "*") //para cargar los datos de la bd en el front
@RestController
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/api/enrollment")
    public ResponseEntity<Enrollment> saveEnrollment(@RequestBody Enrollment enrollment) {
        try{
            Enrollment save = enrollmentService.saveEnrollment(enrollment);
            return ResponseEntity.ok(save);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/api/enrollment/{id}")
    public ResponseEntity<Enrollment> deleteEnrollment(@PathVariable Long id) {
        try {
            enrollmentService.deleteEnrollmentById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/enrollment")
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        try {
            List<Enrollment> getAll = enrollmentService.getAllEnrollments();
            return ResponseEntity.ok(getAll);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/api/enrollment/detail/{id}")
    public ResponseEntity<Map<String, String>> getEnrollmentDetail(@PathVariable Long id) {
        try {
            Map<String, String> enrollmentDetails = enrollmentService.getEnrollmentDetailsById(id);
            return ResponseEntity.ok(enrollmentDetails);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/enrollment/{id}")
    public ResponseEntity<Enrollment> getIdEnrollment(@PathVariable Long id){
        try {
            Enrollment getEnrollmentId = enrollmentService.getIdEnrollment(id);
            return ResponseEntity.ok(getEnrollmentId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/enrollment/update/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        try {
            Enrollment updateEnrollment = enrollmentService.updateEnrollment(
                    id,
                    enrollment.getDate());
            return ResponseEntity.ok(updateEnrollment);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/student/{studentId}/courses")
    public ResponseEntity<List<Map<String, String>>> getCoursesByStudentId(@PathVariable Long studentId) {
        try {
            List<Map<String, String>> courses = enrollmentService.getCoursesByStudentId(studentId);
            return ResponseEntity.ok(courses);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
