package com.school.vaccinationportal.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.vaccinationportal.Repository.StudentRepository;
import com.school.vaccinationportal.Repository.VaccinationDriveRepository;
import com.school.vaccinationportal.Repository.VaccinationRecordRepository;
import com.school.vaccinationportal.model.VaccinationDrive;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private VaccinationDriveRepository vaccinationDriveRepository;

    @Autowired
    private VaccinationRecordRepository vaccinationRecordRepository;


    public long getStudentCount() {
        return studentRepository.count();
    }
    
    public long getVaccinatedStudentCount() {
        return vaccinationRecordRepository.countDistinctByStudent();
    }

    public long getVaccinatedStudentPercentage() {
        return (getVaccinatedStudentCount() * 100) / getStudentCount(); 
    }

    public List<VaccinationDrive> fetchUpcomingDrives() {
        return vaccinationDriveRepository.findAllByDriveDateBetween(LocalDate.now(), LocalDate.now().plusDays(30));
    }
    
}
