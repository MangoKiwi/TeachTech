package com.mangokiwi.controller;

import com.mangokiwi.core.annotation.HandleEmptyRequestBody;
import com.mangokiwi.model.Appointment;
import com.mangokiwi.model.AppointmentStatus;
import com.mangokiwi.model.User;
import com.mangokiwi.service.AppointmentService;
import com.mangokiwi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by zhenfeng on 5/17/17.
 */
@RestController
public class AppointmentController extends BaseController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = APPOINTMENT_PATH + "/{id}", method = RequestMethod.GET)
	public Appointment getAppointmentWithId(@PathVariable Long id) {
		return appointmentService.getAppointmentById(id);
	}


	@RequestMapping(value = APPOINTMENT_PATH, method = RequestMethod.GET)
	public List<Appointment> getAppointments(
			@RequestParam(name = "student_id", required = false) Long studentId,
			@RequestParam(name = "teacher_id", required = false) Long teacherId) {
		List<Appointment> result;
		if (studentId != null && teacherId != null)
			result = appointmentService.getAppointmentsByStudentIdAndTeacherId(
					teacherId, studentId);
		else if (studentId != null)
			result = appointmentService.getAppointmentsByStudentId(studentId);
		else if (teacherId != null)
			result = appointmentService.getAppointmentsByTeacherId(teacherId);
		else
			result = appointmentService.getAllAppointments();
		return result;
	}

	@RequestMapping(value = APPOINTMENT_PATH, method = RequestMethod.POST)
	@HandleEmptyRequestBody(includingFields = {"studentId", "teacherId"})
	public Appointment createNewAppointment(@RequestBody Appointment appointment) {
		User teacher = userService.getUserById(appointment.getTeacherId());
		User student = userService.getUserById(appointment.getStudentId());
		appointment.setStudent(student);
		appointment.setTeacher(teacher);
		if (appointment.getDate() == null)
			appointment.setDate(new Date());
		appointment.setStatus(AppointmentStatus.SCHEDULED);
		appointment.setRating(null);
		appointment = appointmentService.add(appointment);
		return appointment;
	}

	@RequestMapping(value = APPOINTMENT_PATH + "/{id}", method = RequestMethod.PUT)
	@HandleEmptyRequestBody(includingFields = {"studentId", "teacherId"})
	public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
		User teacher = userService.getUserById(appointment.getTeacherId());
		User student = userService.getUserById(appointment.getStudentId());
		appointment.setTeacher(teacher);
		appointment.setStudent(student);
		appointment.setId(id);
		appointment = appointmentService.update(appointment);
		return appointment;
	}

	@RequestMapping(value = APPOINTMENT_PATH + "/{id}", method = RequestMethod.DELETE)
	public void cancelAppointment(@PathVariable Long id){
		appointmentService.deleteAppoinementById(id);
	}
}
