package com.miguelmallqui.studentenrollmen.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository {
    @PersistenceContext
    EntityManager dataBase;
}
