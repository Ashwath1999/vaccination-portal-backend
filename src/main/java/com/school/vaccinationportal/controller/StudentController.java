package com.school.vaccinationportal.controller;

import java.io.IOException;
import java.util.List;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.vaccinationportal.model.Student;
import com.school.vaccinationportal.services.StudentService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> fetchAllStudents() {
        return ResponseEntity.ok(studentService.fetchAllStudents());
    }

    @PostMapping
    public ResponseEntity<JSONObject> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student studentDetails) {
        return studentService.updateStudent(id, studentDetails) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        return studentService.deleteStudent(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Student> searchStudents(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String grade,
        @RequestParam(required = false) Long id
    ) {
        return studentService.searchStudents(name, grade, id);
    }

    @PostMapping("/bulk")
    public String bulkUpload(@RequestParam("file") MultipartFile file) {
        try {
            studentService.bulkUploadStudents(file);
            return "Students successfully uploaded!";
        } catch (IOException e) {
            return "Failed to upload students: " + e.getMessage();
        }
    }

    @PutMapping("/{id}/vaccinate")
    public ResponseEntity<String> vaccinateStudent(@PathVariable Long id, @RequestParam String driveId) {
        try {
            studentService.markAsVaccinated(id, driveId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
