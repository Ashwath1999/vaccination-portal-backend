package com.school.vaccinationportal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.vaccinationportal.model.Student;
import com.school.vaccinationportal.model.VaccinationDrive;
import com.school.vaccinationportal.model.VaccinationRecord;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
    
    boolean existsByStudentAndVaccinationDrive(Student student, VaccinationDrive vaccinationDrive);

    List<VaccinationRecord> findAllByVaccinationDrive(VaccinationDrive vaccinationDrive);

    @Query("SELECT COUNT(DISTINCT v.student) FROM VaccinationRecord v")
    long countDistinctByStudent();
}
