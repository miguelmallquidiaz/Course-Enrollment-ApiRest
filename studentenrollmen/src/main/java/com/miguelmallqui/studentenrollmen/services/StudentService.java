package com.miguelmallqui.studentenrollmen.services;

import com.miguelmallqui.studentenrollmen.models.Student;
import com.miguelmallqui.studentenrollmen.repository.StudentRepositoryPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class StudentService {
    private final StudentRepositoryPost studentRepository;

    @Autowired

    public StudentService(StudentRepositoryPost studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student updateStudent(Long id, String name, String email, String phone) {
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            throw new NoSuchElementException("Student with ID " + id + " not found");
        }

        existingStudent.setName(name);
        existingStudent.setEmail(email);
        existingStudent.setPhone(phone);

        return studentRepository.save(existingStudent);
    }
    public Student getStudent(Long id){
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
