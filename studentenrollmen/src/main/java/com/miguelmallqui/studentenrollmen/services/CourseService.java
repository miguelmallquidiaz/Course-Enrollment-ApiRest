package com.miguelmallqui.studentenrollmen.services;

import com.miguelmallqui.studentenrollmen.models.Course;
import com.miguelmallqui.studentenrollmen.models.Teacher;
import com.miguelmallqui.studentenrollmen.repository.CourseRepositorypost;
import com.miguelmallqui.studentenrollmen.repository.TeacherRepositoryPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepositorypost courseRepositoryPost;

    @Autowired
    public CourseService(CourseRepositorypost courseRepositoryPost) {
        this.courseRepositoryPost = courseRepositoryPost;
    }
    /**
     public Course saveCourse(Course course){
     return courseRepositoryPost.save(course);
     }*/

    public List<Course> getAllCourses() {
        return courseRepositoryPost.findAll();
    }

    public Course getIdCourse(Long id) {
        return courseRepositoryPost.getById(id);
    }

    public void deleteCourseById(Long id) {
        courseRepositoryPost.deleteById(id);
    }

    @Autowired
    private TeacherRepositoryPost teacherRepositoryPost;

    public Course updateCourse(Long id, String courseName, String courseDescription, Date startDateCourse, Date endDateCourse, Long newTeacherId) {
        Course existingCourse = courseRepositoryPost.findById(id).orElse(null);

        if (existingCourse == null) {
            throw new NoSuchElementException("Course with ID " + id + " not found");
        }

        existingCourse.setName(courseName == null ? existingCourse.getName() : courseName);
        existingCourse.setDescription(courseDescription == null ? existingCourse.getDescription() : courseDescription);
        existingCourse.setStartDate(startDateCourse == null ? existingCourse.getStartDate() : startDateCourse);
        existingCourse.setEndDate(endDateCourse == null ? existingCourse.getEndDate() : endDateCourse);

        // Obtén el nuevo profesor por su ID
        Optional<Teacher> newProfessorOptional = teacherRepositoryPost.findById(newTeacherId);
        if (newProfessorOptional.isPresent()) {
            existingCourse.setTeacher(newProfessorOptional.get());
        } else {
            throw new NoSuchElementException("Professor with ID " + newTeacherId + " not found");
        }

        return courseRepositoryPost.save(existingCourse);
    }


    public String getTeacherNameByCourseId(Long courseId) {
        Course course = courseRepositoryPost.findById(courseId)
                .orElseThrow(NoSuchElementException::new);

        String teacherName = course.getTeacher().getName();
        return teacherName;
    }
}
