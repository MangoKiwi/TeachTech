package com.mangokiwi.service;

import com.mangokiwi.core.annotation.HandleEntityNotFound;
import com.mangokiwi.model.Appointment;
import com.mangokiwi.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhenfeng on 5/10/17.
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @HandleEntityNotFound
    public Appointment getAppointmentById(Long id){
        return appointmentRepository.findById(id);
    }

    @HandleEntityNotFound
    public List<Appointment> getAppointmentsByStudentIdAndTeacherId(Long teacherId, Long studentId){
        return appointmentRepository.findByStudentIdAndTeacherId(studentId, teacherId);
    }

    @HandleEntityNotFound
    public List<Appointment> getAppointmentsByStudentId(Long studentId){
        return appointmentRepository.findByStudentId(studentId);
    }

    @HandleEntityNotFound
    public List<Appointment> getAppointmentsByTeacherId(Long teacherId){
        return appointmentRepository.findByTeacherId(teacherId);
    }

    @HandleEntityNotFound
    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public Appointment update(Appointment appointment){
        return  appointmentRepository.save(appointment);
    }

    public Appointment add(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public void deleteAppoinementById(Long id){
        appointmentRepository.deleteById(id);
    }



}
