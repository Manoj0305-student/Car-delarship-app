package com.vw.service;

import com.vw.dao.AppointmentRepo;
import com.vw.dao.CarRepo;
import com.vw.dto.AppointmentDto;
import com.vw.entities.Appointment;
import com.vw.entities.Car;
import com.vw.exceptions.AppointmentException;
import com.vw.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepository;

    @Autowired
    private CarRepo carRepository;

    @Autowired
    EmailService emailService;

    public AppointmentService(AppointmentRepo appointmentRepository, CarRepo carRepository) {
        this.appointmentRepository = appointmentRepository;
        this.carRepository = carRepository;
    }

    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto getAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id + " not found!"));
        return convertToDto(appointment);
    }

    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        if (!isValidDlNumber(appointmentDto.getDlNumber())) {
            throw new AppointmentException("Invalid DL Number");
        }
        if(appointmentRepository.existsByAppointmentDate(appointmentDto.getAppointmentDate())){
            throw new AppointmentException("Slot is not empty. Car already booked!!");
        }
        Appointment appointment = convertToEntity(appointmentDto);
        Appointment savedAppointment = appointmentRepository.save(appointment);

        // Send confirmation email
        emailService.sendEmail(appointmentDto.getEmail(), "Test Drive Confirmation",
                "An executive will contact you for further details.");

        return convertToDto(savedAppointment);
    }

    private boolean isCarAvailable(Car car, Date appointmentDate) {
        List<Appointment> appointments = appointmentRepository.findAll();
        for (Appointment appoint:appointments) {
            if(appoint.getCar().equals(car) && appoint.getAppointmentDate().equals(appointmentDate))
                return false;
        }
        return true;
    }

    private boolean isValidDlNumber(String dlNumber) {
        String regex = "^(([A-Z]{2}[0-9]{2})( )|([A-Z]{2}-[0-9]{2}))((19|20)[0-9][0-9])[0-9]{7}$";
        return Pattern.matches(regex,dlNumber);
    }

    public void deleteAppointment(int id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        } else {
            throw new IdNotFoundException(id + " not found for delete operation!");
        }
    }

    public AppointmentDto updateAppointment(int id, AppointmentDto appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id + " not found!"));

        appointment.setCustomerName(appointmentDetails.getCustomerName());
        appointment.setCustomerContact(appointmentDetails.getCustomerContact());
        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setAppointmentType(appointmentDetails.getAppointmentType());

        Car car = carRepository.findById( appointmentDetails.getCarId())
                .orElseThrow(() -> new IdNotFoundException("Car not found!"));
        appointment.setCar(car);

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return convertToDto(updatedAppointment);
    }

    private AppointmentDto convertToDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getAppointmentId());
        appointmentDto.setCustomerName(appointment.getCustomerName());
        appointmentDto.setCustomerContact(appointment.getCustomerContact());
        appointmentDto.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDto.setAppointmentType(appointment.getAppointmentType());
        appointmentDto.setEmail(appointment.getEmail());
        appointmentDto.setDlNumber(appointment.getDlNumber());
        appointmentDto.setCarId(appointment.getCar().getCarId());
        return appointmentDto;
    }

    private Appointment convertToEntity(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setCustomerName(appointmentDto.getCustomerName());
        appointment.setCustomerContact(appointmentDto.getCustomerContact());
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentType(appointmentDto.getAppointmentType());
        appointment.setDlNumber(appointmentDto.getDlNumber());
        appointment.setEmail(appointmentDto.getEmail());

        Car car = carRepository.findById(appointmentDto.getCarId())
                .orElseThrow(() -> new IdNotFoundException("Car not found!"));
        appointment.setCar(car);

        return appointment;
    }
}