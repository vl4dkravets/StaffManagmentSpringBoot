package com.example.StaffManagment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StaffManagment.dao.StaffManagmentDao;
import com.example.StaffManagment.entities.Person;

@Service
public class StaffManagmentService {
	
	@Autowired
	private StaffManagmentDao smDao;
	
	public Person createPerson(Person person1) {
		return smDao.save(person1);
	}
	
	public Iterable createPeople(List<Person> peopleList1) {
		Iterable<Person> res = smDao.saveAll(peopleList1);
		return res;
	}

	public Iterable<Person> getPeopleById(List<Integer> ids) {
		
		return smDao.findAllById(ids);
	}

	public void deletePerson(Person person1) {
		smDao.delete(person1);
	}

	public void updatePerson(int id, String newEmail) {
		Optional<Person> person1 = smDao.findById(id);
		Person p;
		if(person1.isPresent()) {
			p = person1.get();
		}
		else {
			return;
		}
		
		if(p.getId() == id) {
			p.setEmail(newEmail);
		}
		smDao.save(p);
	}

	public List<Person> getPersonInfoByLastName(String lastName) {
		return smDao.findByLastName(lastName);
	}

	public List<Person> getPersonByLastNameOrEmail(String lastName, String email) {
		return smDao.findByLastNameOrEmail(lastName, email);
	}
	
	public List<Person> getDataViaNamedQueryAnnotat(int id) {
		return smDao.getDataViaNamedQueryAnnotat(id);
	}
	
	public List<Person> getDataViaQueryAnnotat(String lastName) {
		return smDao.getDataViaQueryAnnotat(lastName);
	}
}
