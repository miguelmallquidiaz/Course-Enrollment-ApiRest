package com.miguelmallqui.studentenrollmen.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class CourseRepository {
    @PersistenceContext
    EntityManager database;

    @Transactional
    public void saveCourse(String courseName, String courseDescription, Date starDateCourse, Date endDateCourse, Long idTeacher){
        try {

            String sqlQuery = "INSERT INTO course (course_name, description, start_date, end_date, id_professor) VALUES (:nameParametro, :descriptionParametro, :starDateParametro, :endDateParametro, :idProfessorParametro)";

            database.createNativeQuery(sqlQuery)
                    .setParameter("nameParametro", courseName)
                    .setParameter("descriptionParametro", courseDescription)
                    .setParameter("starDateParametro", starDateCourse)
                    .setParameter("endDateParametro", endDateCourse)
                    .setParameter("idProfessorParametro", idTeacher)
                    .executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
