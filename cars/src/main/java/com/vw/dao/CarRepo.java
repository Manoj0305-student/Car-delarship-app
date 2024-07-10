package com.vw.dao;

import java.util.List;

//import java.util.List;

import com.vw.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer>{
	
//	@Query("select c from Car c where name =:val")
//	public Car findName(@Param("val")String name);
	public List<Car> findByName(String name);
	public List<Car> findByModel(int model);
	public List<Car> findByBrand(String brand);
	

}
