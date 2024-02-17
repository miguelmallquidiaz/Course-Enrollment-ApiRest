package com.miguelmallqui.studentenrollmen.models;

import jakarta.persistence.*;
import lombok.Data;

@Data //anotación para automatizar
@Entity //como sera mapeada la base de datos
@Table(name = "student")//Mapadeada en una tabla de BD
public class Student {
    @Id//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//incrementa automaticamente el pk
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name")
    private String name;

    //La columna debe tener un valor unico y no un valor vacío.
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;
}
