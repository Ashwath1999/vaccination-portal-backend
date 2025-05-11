package com.school.vaccinationportal.response;

public class ApplicableStudentResponse {
    private Long id;
    private String name;
    private int grade;
    private boolean vaccinationStatus;

    public ApplicableStudentResponse(Long id, String name, int grade, boolean vaccinationStatus) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.vaccinationStatus = vaccinationStatus;
    }

    public Long getStudentId() {
        return id;
    }

    public String getStudentName() {
        return name;
    }

    public int getStudentGrade() {
        return grade;
    }
    
    public boolean isVaccinated() {
        return vaccinationStatus;
    }

}
