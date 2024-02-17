package com.miguelmallqui.studentenrollmen.repository;

import com.miguelmallqui.studentenrollmen.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);

}
