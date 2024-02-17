package com.miguelmallqui.studentenrollmen.services;

import com.miguelmallqui.studentenrollmen.models.Course;
import com.miguelmallqui.studentenrollmen.models.Enrollment;
import com.miguelmallqui.studentenrollmen.repository.CourseRepositorypost;
import com.miguelmallqui.studentenrollmen.repository.EnrollmentRepository;
import com.miguelmallqui.studentenrollmen.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepositorypost courseRepositoryPost;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollmentById(Long id) {
        enrollmentRepository.deleteById(id);
    }



    public Map<String, String> getEnrollmentDetailsById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment not found"));

        Map<String, String> enrollmentDetails = new HashMap<>();
        enrollmentDetails.put("courseName", enrollment.getCourse().getName());
        enrollmentDetails.put("studentName", enrollment.getStudent().getName());
        return enrollmentDetails;
    }


    public List<Map<String, String>> getCoursesByStudentId(Long studentId) {
        List<Map<String, String>> courses = new ArrayList<>();

        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        for (Enrollment enrollment : enrollments) {
            Long courseId = enrollment.getCourse().getId();
            Course course = courseRepositoryPost.findById(courseId)
                    .orElseThrow(() -> new NoSuchElementException("Course not found"));

            Map<String, String> courseInfo = new HashMap<>();
            courseInfo.put("course_id", courseId.toString());
            courseInfo.put("course_name", course.getName());
            courses.add(courseInfo);
        }

        return courses;
    }



    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getIdEnrollment(Long id) {
        return enrollmentRepository.getById(id);
    }

    public Enrollment updateEnrollment(Long id, Date dateEnrollment){
        Enrollment existingEnrollment = enrollmentRepository.findById(id).orElse(null);

        if (existingEnrollment == null) {
            throw new NoSuchElementException(String.format("Enrollment with ID %s not found", id));
        }
        System.out.println();
        existingEnrollment.setDate(dateEnrollment == null ? existingEnrollment.getDate() : dateEnrollment);
        existingEnrollment.setCourse(existingEnrollment.getCourse());
        existingEnrollment.setStudent(existingEnrollment.getStudent());

        return enrollmentRepository.save(existingEnrollment);
    }
}
