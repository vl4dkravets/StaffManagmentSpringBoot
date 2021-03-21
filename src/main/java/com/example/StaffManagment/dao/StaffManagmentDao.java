package com.example.StaffManagment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.StaffManagment.entities.Person;

public interface StaffManagmentDao extends CrudRepository<Person, Integer>{
	
	List<Person> findByLastName(String lastName);

	List<Person> findByLastNameOrEmail(String lastname, String email);
	
	List<Person> getDataViaNamedQueryAnnotat(int id);
	
	@Query(value="SELECT p FROM Person p WHERE p.lastName=?1")
	List<Person> getDataViaQueryAnnotat(String lastName);
}
