package com.school.vaccinationportal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.vaccinationportal.model.VaccinationDrive;
import com.school.vaccinationportal.services.DashboardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService DashboardService;

    @GetMapping("/students-count")
    public ResponseEntity<Long> getStudentCount() {
         return ResponseEntity.ok(DashboardService.getStudentCount());
    }
    
    @GetMapping("/vaccinated-count")
    public ResponseEntity<Long> getVaccinatedStudentCount() {
         return ResponseEntity.ok(DashboardService.getVaccinatedStudentCount());
    }

    @GetMapping("/vaccinated-percentage")
    public ResponseEntity<Long> getVaccinatedStudentPercentage() {
         return ResponseEntity.ok(DashboardService.getVaccinatedStudentPercentage());
    }

    @GetMapping("/upcoming-drives")
    public ResponseEntity<List<VaccinationDrive>> fetchUpcomingDrives() {
         return ResponseEntity.ok(DashboardService.fetchUpcomingDrives());
    }
    
}
