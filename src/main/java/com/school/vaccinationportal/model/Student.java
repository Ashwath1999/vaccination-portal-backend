package com.school.vaccinationportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import com.school.vaccinationportal.enums.Gender;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(name = "student_grade", nullable = false)
    private String grade;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_gender", nullable = false)
    private Gender gender;


    protected Student() {
    }

    public Student(String name, Gender gender, String grade) {
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }


    public long getId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public Gender getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}