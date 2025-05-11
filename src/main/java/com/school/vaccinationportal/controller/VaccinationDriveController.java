package com.school.vaccinationportal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.vaccinationportal.model.VaccinationDrive;
import com.school.vaccinationportal.response.ApplicableStudentResponse;
import com.school.vaccinationportal.services.VaccinationDriveService;
import com.school.vaccinationportal.services.VaccinationRecordService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/vaccination-drives")
public class VaccinationDriveController {

    @Autowired
    private VaccinationDriveService vaccinationDriveService;

    @Autowired
    private VaccinationRecordService vaccinationRecordService;

    @PostMapping
    public ResponseEntity<String> addVaccinationDrive(@RequestBody VaccinationDrive vaccinationDrive) {
        try {
            vaccinationDriveService.addVaccinationDrive(vaccinationDrive);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVaccinationDrive(@PathVariable long id) {
        return vaccinationDriveService.deleteVaccinationDrive(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateVaccinationDrive(@PathVariable long id, @RequestBody VaccinationDrive vaccinationDrive) {
        try {
            vaccinationDriveService.updateVaccinationDrive(id, vaccinationDrive);
            return ResponseEntity.noContent().build();
        } catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<VaccinationDrive>> fetchVaccinationDrives() {
        return ResponseEntity.ok(vaccinationDriveService.fetchVaccinationDrives());
    }

    @GetMapping("/{id}/applicable-students")
    public ResponseEntity<ArrayList<ApplicableStudentResponse>> getApplicableStudents(@PathVariable long id) {
        try {
            return ResponseEntity.ok(vaccinationRecordService.getVaccinationRecords(id));
        } catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }   
} 
