package com.miguelmallqui.studentenrollmen.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TEACHER")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;

    @Column(name = "teacher_name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false,unique = true)
    private String phone;
}
