package com.miguelmallqui.studentenrollmen.services;

import com.miguelmallqui.studentenrollmen.models.Teacher;
import com.miguelmallqui.studentenrollmen.repository.TeacherRepositoryPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TeacherService {
    private final TeacherRepositoryPost teacherRepositoryPost;

    @Autowired
    public TeacherService(TeacherRepositoryPost teacherRepositoryPost) {
        this.teacherRepositoryPost = teacherRepositoryPost;
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepositoryPost.save(teacher);
    }

    public List<Teacher> getAllTeacher() {
        return teacherRepositoryPost.findAll();
    }

    public Teacher getIdTeacher(Long id) {
        return teacherRepositoryPost.findById(id).orElse(null);
    }

    public void deleteTeacherById(Long id) {
        teacherRepositoryPost.deleteById(id);
    }

    public Teacher updateProfessor(Long id, String teacherName, String teacherEmail, String teacherPhone) {
        Teacher existingProfessor = teacherRepositoryPost.findById(id).orElse(null);

        if (existingProfessor == null) {
            throw new NoSuchElementException(String.format("Teacher with id %s not found.", id));
        }

        existingProfessor.setName(teacherName == null ? existingProfessor.getName() : teacherName);
        existingProfessor.setEmail(teacherEmail == null ? existingProfessor.getEmail() : teacherEmail);
        existingProfessor.setPhone(teacherPhone == null ? existingProfessor.getPhone() : teacherPhone);

        return teacherRepositoryPost.save(existingProfessor);
    }
}
