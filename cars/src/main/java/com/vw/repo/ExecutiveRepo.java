package com.vw.repo;

import com.vw.entities.Executive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutiveRepo extends JpaRepository<Executive,Integer> {
}
