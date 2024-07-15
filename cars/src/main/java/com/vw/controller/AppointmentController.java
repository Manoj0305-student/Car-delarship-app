package com.vw.controller;

import com.vw.dto.AppointmentDto;
import com.vw.exceptions.AppointmentException;
import com.vw.exceptions.ListOfCarIsEmptyException;
import com.vw.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Create a new appointment
    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto createdAppointment = appointmentService.createAppointment(appointmentDto);
        return ResponseEntity.ok(createdAppointment);
    }

    // Create a new test drive appointment
    @PostMapping("/test-drive")
    public ResponseEntity<AppointmentDto> createTestDriveAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto createdAppointment = appointmentService.createTestDriveAppointment(appointmentDto);
        return ResponseEntity.ok(createdAppointment);
    }

    // Update an existing appointment
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable int id, @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto updatedAppointment = appointmentService.updateAppointment(id, appointmentDto);
        return ResponseEntity.ok(updatedAppointment);
    }

    // Delete an appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

    // Get all appointments
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.getAllAppointments();
        if(appointments.isEmpty()) {
            throw new AppointmentException("No Data Found!!");
        }
        return ResponseEntity.ok(appointments);
    }

    // Get an appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable int id) {
        AppointmentDto appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    // Get appointments by executive ID
    @GetMapping("/executive/{executiveId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByExecutive(@PathVariable int executiveId) {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByExecutive(executiveId);
        return ResponseEntity.ok(appointments);
    }

    // Book an appointment with a direct executive
    @PostMapping("/direct-executive")
    public ResponseEntity<AppointmentDto> bookAppointmentWithDirectExecutive(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto bookedAppointment = appointmentService.bookAppointmentWithDirectExecutive(appointmentDto);
        return ResponseEntity.ok(bookedAppointment);
    }

    // Buy a car
    @PostMapping("/buy-car/{appointmentId}")
    public ResponseEntity<Void> buyACar(@PathVariable int appointmentId) {
        appointmentService.buyACar(appointmentId);
        return ResponseEntity.ok().build();
    }
}
