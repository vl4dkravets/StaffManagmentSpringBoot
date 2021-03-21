package com.example.StaffManagment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.StaffManagment.entities.Person;
import com.example.StaffManagment.service.StaffManagmentService;

@SpringBootApplication
public class StaffmanagmentSpringBootApplication implements CommandLineRunner {

	@Autowired
	private StaffManagmentService smService;
	
	public static void main(String[] args) {
		SpringApplication.run(StaffmanagmentSpringBootApplication.class, args);
	}

	/**
	 * Testing various CRUD methods available in Spring Data JPA
	 */
	
	@Override
	public void run(String... args) throws Exception {
		// createPerson();
		// getPeopleById();
		// deletePerson();
		// updatePerson();
		
		// getPersonInfoByLastName();
		// getPersonByLastNameOrEmail();
		// getDataViaNamedQueryAnnotat();
		getDataViaQueryAnnotat();
	}
	
	/**
	 * Method meant to use @Query annotation over one of the repo's methods
	 * to retrieve data, specified in the @Query's SQL
	 */
	
	private void getDataViaQueryAnnotat() {
		List<Person> personList = smService.getDataViaQueryAnnotat("Doe");
		personList.forEach(System.out::println);
		
	}

	/**
	 * Method meant to use @NamedQuery annotation linked with the entity;
	 * Annotation specifies the name of the method which is called to access DB 
	 * (one of the methods from Repository implementation) & it contains the SQL 
	 * command to execute upon calling that method
	 */
	
	private void getDataViaNamedQueryAnnotat() {
		List<Person> personList = smService.getDataViaNamedQueryAnnotat(3);
		personList.forEach(System.out::println);
	}
	
	/**
	 * Method which uses Spring Data feature to build queries based on the method name you specify
	 * in your Repository interface; in this case, we retrieve data based on 2 input, with operator OR
	 */	
	private void getPersonByLastNameOrEmail() {
		List<Person> personList = smService.getPersonByLastNameOrEmail("Smith", "new2@email.com");
		personList.forEach(System.out::println);
	}

	/**
	 * One more method which uses Spring Data feature to build queries based on the method name;
	 */
	private void getPersonInfoByLastName() {
		List<Person> personList = smService.getPersonInfoByLastName("Doe");
		personList.forEach(System.out::println);
	}

	/**
	 * Example of updating a row in DB via save method from CRUD interface
	 */	
	private void updatePerson() {
		smService.updatePerson(2, "vlad@kravets.com");
	}
	
	/**
	 * Using delete() method from CRUD interface
	 */
	private void deletePerson() {
		Person person1 = new Person();
		person1.setId(1);
		smService.deletePerson(person1);
		
	}

	/**
	 * Using findAllById() fom CRUD inteface
	 */
	
	private void getPeopleById() {
		List<Integer> ids = new ArrayList<>();
		ids.add(1);
		ids.add(3);
		ids.add(20);
		Iterable<Person> personList = smService.getPeopleById(ids);
		personList.forEach(System.out::println);
	}
	
	/**
	 * Using save() from CRUD interface	
	 */

	private void createPerson() {
		Person person1 = new Person("John",
									"Doe",
									"sean@murphy.com",
									new Date());
		Person personFromDb = smService.createPerson(person1);
		System.out.println(personFromDb);
	}
	
	/**
	 * Using saveAll() from CRUD interface	
	 */
	private void createPeople() {
		List<Person> peopleList = Arrays.asList(
				new Person("Vlad",
						"Kravets",
						"vlad@kravets.com",
						new Date()),
				new Person("John",
						"Doe",
						"john@doe.com",
						new Date()));
		
		Iterable<Person> res = smService.createPeople(peopleList);
		
		for(Person p1: res) {
			System.out.println(p1);
		}
	}
}
