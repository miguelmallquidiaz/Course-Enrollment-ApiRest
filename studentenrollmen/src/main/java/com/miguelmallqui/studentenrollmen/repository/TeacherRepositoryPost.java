package com.miguelmallqui.studentenrollmen.repository;

import com.miguelmallqui.studentenrollmen.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepositoryPost extends JpaRepository<Teacher, Long> {
    List<Teacher> findByActiveTrue();
}
