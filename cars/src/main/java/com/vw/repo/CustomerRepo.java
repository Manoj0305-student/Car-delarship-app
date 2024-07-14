package com.vw.repo;

import com.vw.entities.Customer;
import com.vw.entities.Executive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    List<Customer> findByExecutive(Executive executive);
}
