package com.mangokiwi.repository;

import com.mangokiwi.model.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhenfeng on 5/10/17.
 */

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Long> {

    Appointment findById(Long id);

    @Query("SELECT '*' FROM Appointment WHERE student_id = :student_id AND teacher_id = :teacher_id")
    List<Appointment> findByStudentIdAndTeacherId(@Param("student_id") Long studentId,
                                                  @Param("teacher_id") Long teacherId);

    @Query("SELECT '*' FROM Appointment WHERE student_id = :student_id")
    List<Appointment>findByStudentId(@Param("student_id") Long studentId);

    @Query("SELECT '*' FROM Appointment WHERE teacher_id = :teacher_id")
    List<Appointment>findByTeacherId(@Param("teacher_id") Long teacherId);

    void deleteById(Long id);
}
