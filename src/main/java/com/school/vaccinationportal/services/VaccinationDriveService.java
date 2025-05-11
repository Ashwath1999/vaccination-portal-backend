package com.school.vaccinationportal.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.vaccinationportal.Repository.VaccinationDriveRepository;
import com.school.vaccinationportal.model.VaccinationDrive;

@Service
public class VaccinationDriveService {

    @Autowired
    private VaccinationDriveRepository vaccinationDriveRepository;
    
     public VaccinationDrive getVaccinationDrive(String vaccineName) {
        Optional<VaccinationDrive> vaccineDrive = vaccinationDriveRepository.findByVaccineName(vaccineName);
        if (vaccineDrive.isPresent()) {
            return vaccineDrive.get();
        } else {
            throw new RuntimeException("Drive not found for the given vaccine name");
        }
    }

    public List<VaccinationDrive> fetchVaccinationDrives() {
        return vaccinationDriveRepository.findAll();
    }

    public void addVaccinationDrive(VaccinationDrive vaccinationDrive) {
        LocalDate driveDate = vaccinationDrive.getDriveDate();
        validateDriveDate(driveDate);
        verifyDriveDateAvailability(driveDate);
        vaccinationDriveRepository.save(vaccinationDrive);
    }

    public void updateVaccinationDrive(Long id, VaccinationDrive modifiedDrive) {
        Optional<VaccinationDrive> vaccinationDriveOptional = vaccinationDriveRepository.findById(id);
        if (vaccinationDriveOptional.isPresent()) {
            VaccinationDrive vaccinationDrive = vaccinationDriveOptional.get();
            if (!vaccinationDrive.getVaccineName().equals(modifiedDrive.getVaccineName())) {
                vaccinationDrive.setVaccineName(modifiedDrive.getVaccineName());
            }
            if (vaccinationDrive.getAvailableDoses() != modifiedDrive.getAvailableDoses()) {
                vaccinationDrive.setAvailableDoses(modifiedDrive.getAvailableDoses());
            }
            if (!modifiedDrive.getApplicableGrades().isEmpty()) {
                vaccinationDrive.setApplicableGrades(modifiedDrive.getApplicableGrades());
            }
            if (!vaccinationDrive.getDriveDate().equals(modifiedDrive.getDriveDate())) {
                LocalDate driveDate = modifiedDrive.getDriveDate();
                validateDriveDate(driveDate);
                verifyDriveDateAvailability(driveDate);
                vaccinationDrive.setDriveDate(driveDate);
            }
            vaccinationDriveRepository.save(vaccinationDrive);
        }
    }

    public void verifyDriveDateAvailability(LocalDate inputDate) {
        if (vaccinationDriveRepository.existsByDriveDate(inputDate)) {
            throw new IllegalArgumentException("Another Drive is already scheduled on this date.");
        }
    }

    public void validateDriveDate(LocalDate inputDate) {
        if (inputDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Drive date has already expired");
        } 
        LocalDate minValidDate = LocalDate.now().plusDays(15);
        if (inputDate.isBefore(minValidDate)) {
            throw new IllegalArgumentException("Drive must be scheduled at least 15 days in advance.");
        }
    }

    public boolean deleteVaccinationDrive(Long id) {
        Optional<VaccinationDrive> driveOptional = vaccinationDriveRepository.findById(id);
        if (driveOptional.isPresent()) {
            vaccinationDriveRepository.deleteById(id);
            return true;
        }
        return false;
    }

}