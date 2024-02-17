package com.miguelmallqui.studentenrollmen.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class StudentRepository {
    @PersistenceContext
    EntityManager database;
    @Transactional
    public void addStudent(String studentName, String studentEmail, String studentPhone){
        try {
            String sqlQuery = "INSERT INTO student (student_name, email, phone) VALUES (:nameParametro, :emailParametro, :phoneParametro)";
            database.createNativeQuery(sqlQuery)
                    .setParameter("nameParametro", studentName)
                    .setParameter("emailParametro", studentEmail)
                    .setParameter("phoneParametro", studentPhone)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ocurrió un error al guardar el estudiante en la base de datos.");
        }
    }
}
