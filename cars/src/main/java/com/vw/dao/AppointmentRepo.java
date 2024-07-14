package com.vw.dao;

import com.vw.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

     boolean existsByAppointmentDate(Date date);
     boolean existsByEmail(String email);
}
