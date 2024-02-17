package com.miguelmallqui.studentenrollmen.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})//Puede causar problemas cuando se serealiza un objeto a JSON
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long id;

    //Es anotación marca los campos como relaciones de mucho a uno
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id")
    private Course course;

    //La anotación formatea en el formato especificado cuando se serealiza con JSON
    @Column(name = "EnrollmentDate", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
}
