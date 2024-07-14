package com.vw.service;

import com.vw.dao.AppointmentRepo;
import com.vw.dao.CarRepo;
import com.vw.dto.AppointmentDto;
import com.vw.entities.Appointment;
import com.vw.entities.Car;
import com.vw.exceptions.AppointmentException;
import com.vw.exceptions.IdNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepo appointmentRepo;

    @Mock
    private CarRepo carRepo;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AppointmentService appointmentService;

    private Appointment appointment1;
    private Appointment appointment2;

    private Car car;

    // ,new Date(2024,07,14,12,30,00)
    private List<Appointment> appointmentList = new ArrayList<>();
    @BeforeEach
    void setup(){
        car = new Car(1, "car1", "audi",
                2020, "petrol", "hybrid", "some text",
                "car1.png", "image/png", new byte[]{}, appointmentList);

        appointment1 = new Appointment(1,"Jane Smith","+91 8765432109","jane.smith@example.com",new Date(2024,07,14,12,30,00),"test-drive","KA-0319850034761",car);

        appointment2 = new Appointment(2,"Jane Smith","+91 8765432199","jane.amith@example.com",new Date(2024,8,14,12,30,00),"test-drive","KA-0319850034861",car);

        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
    }

    @Test
    public void testGetAllAppointments() {
        when(appointmentRepo.findAll()).thenReturn(appointmentList);

        List<AppointmentDto> appointments = appointmentService.getAllAppointments();

        assertEquals(2, appointments.size());
        verify(appointmentRepo, times(1)).findAll();
    }

    @Test
    public void testGetAppointmentById() {
        when(appointmentRepo.findById(1)).thenReturn(Optional.of(appointment1));

        AppointmentDto appointmentDto = appointmentService.getAppointmentById(1);

        assertNotNull(appointmentDto);
        assertEquals(1, appointmentDto.getId());
        verify(appointmentRepo, times(1)).findById(1);
    }

    @Test
    public void testGetAppointmentByIdNotFound() {
        when(appointmentRepo.findById(anyInt())).thenReturn(Optional.empty());

        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> appointmentService.getAppointmentById(1));

        assertTrue(exception.getMessage().contains("1 not found!"));
        verify(appointmentRepo, times(1)).findById(1);
    }

    @Test
    public void testCreateAppointment() {    AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDlNumber("KA-0319850034861");    appointmentDto.setAppointmentDate(new Date());
        appointmentDto.setCarId(1);    appointmentDto.setEmail("manojnirmala0305@gmail.com");
        when(carRepo.findById(1)).thenReturn(Optional.of(car));
        when(appointmentRepo.existsByAppointmentDate(any(Date.class))).thenReturn(false);    when(appointmentRepo.save(any(Appointment.class))).thenReturn(appointment1);
        AppointmentDto createdAppointment = appointmentService.createAppointment(appointmentDto);
        assertNotNull(createdAppointment);
        assertEquals(1, createdAppointment.getId());    verify(appointmentRepo, times(1)).save(any(Appointment.class));
        verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());}

    @Test
    public void testCreateAppointmentInvalidDlNumber() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDlNumber("INVALID_DL");

        AppointmentException exception = assertThrows(AppointmentException.class, () -> appointmentService.createAppointment(appointmentDto));

        assertEquals("Invalid DL Number", exception.getMessage());
    }


    @Test
    public void testCreateAppointmentSlotNotEmpty() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDlNumber("KA-0319850034861");
        appointmentDto.setAppointmentDate(new Date());

        when(appointmentRepo.existsByAppointmentDate(any(Date.class))).thenReturn(true);

        AppointmentException exception = assertThrows(AppointmentException.class, () -> appointmentService.createAppointment(appointmentDto));

        assertEquals("Slot is not empty. Car already booked!!", exception.getMessage());
    }

    @Test
    public void testDeleteAppointment() {
        when(appointmentRepo.existsById(1)).thenReturn(true);

        appointmentService.deleteAppointment(1);
        verify(appointmentRepo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteAppointmentNotFound() {
        when(appointmentRepo.existsById(1)).thenReturn(false);

        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> appointmentService.deleteAppointment(1));

        assertTrue(exception.getMessage().contains("1 not found for delete operation!"));
    }

    @Test
    public void testUpdateAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setCustomerName("New Name");
        appointmentDto.setCarId(1);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1);
        appointment.setCustomerName("Old Name");

        Car car = new Car();
        car.setId(1);

        when(appointmentRepo.findById(1)).thenReturn(Optional.of(appointment));
        when(carRepo.findById(1)).thenReturn(Optional.of(car));
        when(appointmentRepo.save(any(Appointment.class))).thenReturn(appointment);

        AppointmentDto updatedAppointment = appointmentService.updateAppointment(1, appointmentDto);

        assertNotNull(updatedAppointment);
        assertEquals("New Name", updatedAppointment.getCustomerName());
        verify(appointmentRepo, times(1)).findById(1);
        verify(appointmentRepo, times(1)).save(any(Appointment.class));
    }

    @Test
    public void testUpdateAppointmentNotFound() {
        AppointmentDto appointmentDto = new AppointmentDto();

        when(appointmentRepo.findById(anyInt())).thenReturn(Optional.empty());

        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> appointmentService.updateAppointment(1, appointmentDto));

        assertTrue(exception.getMessage().contains("1 not found!"));
    }

}