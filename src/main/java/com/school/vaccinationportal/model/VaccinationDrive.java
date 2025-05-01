package com.school.vaccinationportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaccination_drives")
public class VaccinationDrive {

    @Id
    @JoinColumn(name = "drive_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driveId;

    @Column(name = "vaccine_name", nullable = false)
    private String vaccineName;

    @Column(name = "drive_date", nullable = false)
    private LocalDate driveDate;

    @Column(name = "available_doses", nullable = false)
    private int availabeDoses;


    @ElementCollection
    @CollectionTable(name = "vaccination_drive_grades", joinColumns = @JoinColumn(name = "drive_id"))
    @Column(name = "grade")
    private List<Integer> applicableGrades;


    protected VaccinationDrive() {
    }

    public VaccinationDrive(String vaccineName, LocalDate driveDate, int availabeDoses, List<Integer> applicableGrades) {
        this.vaccineName = vaccineName;
        this.driveDate = driveDate;
        this.availabeDoses = availabeDoses;
        this.applicableGrades = applicableGrades;
    }
    

    public long getId() {
        return driveId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public LocalDate getDriveDate() {
        return driveDate;
    }

    public int getAvailableDoses() {
        return availabeDoses;
    }

    public List<Integer> getApplicableGrades() {
        return applicableGrades;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public void setDriveDate(LocalDate date) {
        this.driveDate = date;
    }

    public void setAvailableDoses(int availabeDoses) {
        this.availabeDoses = availabeDoses;
    }

    public void setApplicableGrades(List<Integer> applicableGrades) {
        this.applicableGrades = applicableGrades;
    }
}