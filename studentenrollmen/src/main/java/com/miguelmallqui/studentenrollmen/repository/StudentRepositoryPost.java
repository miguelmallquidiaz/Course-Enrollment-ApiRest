package com.miguelmallqui.studentenrollmen.repository;

import com.miguelmallqui.studentenrollmen.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryPost extends JpaRepository<Student, Long> {
    //Proporciona metodos genéricos para realizar operaciones CRUD
}
