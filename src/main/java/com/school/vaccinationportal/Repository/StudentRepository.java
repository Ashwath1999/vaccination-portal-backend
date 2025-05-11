package com.school.vaccinationportal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.vaccinationportal.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
    @Query("SELECT s FROM Student s WHERE "
     + "(:name IS NULL OR s.name LIKE %:name%) AND "
     + "(:id IS NULL OR s.id = :id) AND "
     + "(:grade IS NULL OR s.grade = :grade)")
    List<Student> search(@Param("id") Long id, @Param("name") String name, @Param("grade") Integer grade);

    @Query("SELECT s FROM Student s WHERE s.grade IN :applicableGrades")
    List<Student> findAllEligibleStudents(@Param("applicableGrades") List<Integer> applicableGrades);
}
