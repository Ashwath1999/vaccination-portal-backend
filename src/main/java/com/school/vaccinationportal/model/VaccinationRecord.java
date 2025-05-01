package com.school.vaccinationportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "vaccination_records")
public class VaccinationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "drive_id", nullable = false)
    private VaccinationDrive vaccinationDrive;

    protected VaccinationRecord() {}

    public VaccinationRecord(Student student, VaccinationDrive vaccinationDrive) {
        this.student = student;
        this.vaccinationDrive = vaccinationDrive;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public VaccinationDrive getVaccinationDrive() {
        return vaccinationDrive;
    }

    public void setVaccinationDrive(VaccinationDrive vaccinationDrive) {
        this.vaccinationDrive = vaccinationDrive;
    }
}