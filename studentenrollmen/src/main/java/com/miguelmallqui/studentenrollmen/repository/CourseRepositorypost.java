package com.miguelmallqui.studentenrollmen.repository;

import com.miguelmallqui.studentenrollmen.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepositorypost extends JpaRepository<Course, Long> {
}
