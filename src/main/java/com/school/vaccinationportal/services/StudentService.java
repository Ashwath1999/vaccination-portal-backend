package com.school.vaccinationportal.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.school.vaccinationportal.Repository.StudentRepository;
import com.school.vaccinationportal.Repository.VaccinationDriveRepository;
import com.school.vaccinationportal.enums.Gender;
import com.school.vaccinationportal.model.Student;
import com.school.vaccinationportal.model.VaccinationDrive;

@Service
public class StudentService {

    
    @Autowired
    private VaccinationDriveRepository vaccinationDriveRepository;

    private final VaccinationRecordService vaccinationRecordService;

    protected StudentService(VaccinationRecordService vaccinationRecordService) {
        this.vaccinationRecordService = vaccinationRecordService;
    }

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public boolean updateStudent(Long id, Student studentDetails) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (!student.getName().equals(studentDetails.getName())) {
                student.setName(studentDetails.getName());
            }
            if (!student.getGender().equals(studentDetails.getGender())) {
                student.setGender(studentDetails.getGender());
            }
            if (student.getGrade() != studentDetails.getGrade()) {
                student.setGrade(studentDetails.getGrade());
            }
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void bulkUploadStudents(MultipartFile file) throws IOException {
        List<Student> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        String firstLine = reader.readLine();
        if (firstLine != null && firstLine.startsWith("\uFEFF")) {
            firstLine = firstLine.substring(1);
            try (CSVParser parser = CSVFormat.DEFAULT.builder().setHeader(firstLine.split(",")).setSkipHeaderRecord(true).build().parse(reader)) {
                for (CSVRecord record : parser) {
                    Gender gender = Gender.MALE.toString().equalsIgnoreCase(record.get("Gender")) ? Gender.MALE : Gender.FEMALE;
                    Student student = new Student(record.get("Name"), gender, Integer.parseInt(record.get("Grade")));
                    students.add(student);
                }
            }
            studentRepository.saveAll(students);
        }
    }

    public List<Student> searchStudents(String name, String grade, Long id) {
        Integer gradeInt = null;
        if (!"".equals(grade)) {
            gradeInt = Integer.parseInt(grade);
        }
        return studentRepository.search(id, name, gradeInt);
    }

    public void markAsVaccinated(Long id, String driveId) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            Optional<VaccinationDrive> vacOptional = vaccinationDriveRepository.findById(Long.parseLong(driveId));
            VaccinationDrive drive = vacOptional.get();
            if (vacOptional.isPresent()) {
                boolean alreadyExists = vaccinationRecordService.isVaccinationRecordAvailable(student, drive);
                if (alreadyExists) {
                    throw new RuntimeException("Student is already vaccinated with this vaccine.");
                } else {
                    vaccinationRecordService.saveVaccinationRecord(student, drive);
                }
            } else {
                throw new RuntimeException("Vaccination Drive not found");
            }
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    public List<Student> fetchAllStudents() {
       return studentRepository.findAll();
    }
}
