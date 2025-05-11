package com.school.vaccinationportal.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.vaccinationportal.model.VaccinationDrive;
import java.time.LocalDate;

public interface VaccinationDriveRepository extends JpaRepository<VaccinationDrive, Long> {
    Optional<VaccinationDrive> findByVaccineName(String vaccineName);
    Optional<VaccinationDrive> findByDriveDate(LocalDate driveDate);
    boolean existsByDriveDate(LocalDate driveDate);
    List<VaccinationDrive> findAllByDriveDateBetween(LocalDate startDate, LocalDate endDate);
}
