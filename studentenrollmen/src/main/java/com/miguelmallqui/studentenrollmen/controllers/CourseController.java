package com.miguelmallqui.studentenrollmen.controllers;

import com.miguelmallqui.studentenrollmen.models.Course;
import com.miguelmallqui.studentenrollmen.repository.CourseRepository;
import com.miguelmallqui.studentenrollmen.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*") //para cargar los datos de la bd en el front
@RestController
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @PostMapping("/api/course")
    public String saveCourse(@RequestBody Course course){

        String courseName = course.getName();
        String courseDescription = course.getDescription();
        Date courseStarDate = course.getStartDate();
        Date courseEndDate = course.getEndDate();
        Long courseIdProfessor = course.getTeacher().getId();

        courseRepository.saveCourse(courseName, courseDescription, courseStarDate, courseEndDate, courseIdProfessor);
        return "curso agregado";
    }

    @GetMapping("/api/course")
    public ResponseEntity<List<Course>> getAllCourse(){
        try {
            List<Course> getAllCourses = courseService.getAllCourses();
            return ResponseEntity.ok(getAllCourses);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/course/{id}")
    public ResponseEntity<Course> getIdCourse(@PathVariable Long id){
        try {
            Course getIdCourse = courseService.getIdCourse(id);
            return ResponseEntity.ok(getIdCourse);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/course/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id){
        try {
            courseService.deleteCourseById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/api/course/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course){
        try {
            Course updatedCourse = courseService.updateCourse(id,
                    course.getName(),
                    course.getDescription(),
                    course.getStartDate(),
                    course.getEndDate(),
                    course.getTeacher().getId()); // Agrega el ID del nuevo profesor
            return ResponseEntity.ok(updatedCourse);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/api/course/{id}/professor")
    public ResponseEntity<String> getTeacherNameByCourseId(@PathVariable Long id) {
        try {
            String teacherName = courseService.getTeacherNameByCourseId(id);
            return ResponseEntity.ok(teacherName);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
