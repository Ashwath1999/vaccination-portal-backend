package com.school.vaccinationportal.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.vaccinationportal.Repository.StudentRepository;
import com.school.vaccinationportal.Repository.VaccinationDriveRepository;
import com.school.vaccinationportal.Repository.VaccinationRecordRepository;
import com.school.vaccinationportal.model.Student;
import com.school.vaccinationportal.model.VaccinationDrive;
import com.school.vaccinationportal.model.VaccinationRecord;
import com.school.vaccinationportal.response.ApplicableStudentResponse;

@Service
public class VaccinationRecordService {

    @Autowired
    private VaccinationRecordRepository vaccinationRecordRepository;

    @Autowired
    private VaccinationDriveRepository vaccinationDriveRepository;

    @Autowired
    private StudentRepository studentRepository;
    
    public boolean isVaccinationRecordAvailable(Student student, VaccinationDrive vaccinationDrive) {
        return vaccinationRecordRepository.existsByStudentAndVaccinationDrive(student, vaccinationDrive);
    }

    public void saveVaccinationRecord(Student student, VaccinationDrive vaccinationDrive) {
        vaccinationRecordRepository.save(new VaccinationRecord(student, vaccinationDrive));
    }

    public ArrayList<ApplicableStudentResponse> getVaccinationRecords(Long vaccinationDriveId) {
        Optional<VaccinationDrive> vaccinationDriveOptional = vaccinationDriveRepository.findById(vaccinationDriveId);
        if (vaccinationDriveOptional.isPresent()) {
            VaccinationDrive vaccinationDrive = vaccinationDriveOptional.get();
            List<Student> applicableStudents = studentRepository.findAllEligibleStudents(vaccinationDrive.getApplicableGrades());
            List<VaccinationRecord> vaccinationDrives = vaccinationRecordRepository.findAllByVaccinationDrive(vaccinationDrive);
            Set<Long> vaccinatedStudents = new HashSet<>();
            for (VaccinationRecord vaccinationRecord : vaccinationDrives) {
                vaccinatedStudents.add(vaccinationRecord.getStudent().getId());
            }
            ArrayList<ApplicableStudentResponse> applicableStudentResponses = new ArrayList<>();
            for (Student student : applicableStudents) {
                applicableStudentResponses.add(new ApplicableStudentResponse(student.getId(), student.getName(), student.getGrade(), vaccinatedStudents.contains(student.getId())));
            }
            return applicableStudentResponses;
        } else {
            throw new RuntimeException("Student not found");
        }
    }
}
