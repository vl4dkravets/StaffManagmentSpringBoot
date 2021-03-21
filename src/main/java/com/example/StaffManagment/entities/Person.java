package com.example.StaffManagment.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="person_table")
@DynamicUpdate
@NamedQuery(name="Person.getSomeInfo", query="SELECT p FROM Person p WHERE p.id >?1")
public class Person {
	
	public Person() {}	
	
	public Person(String firstName, String lastName, String email, Date createAt) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.createAt = createAt;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(length=20, nullable=false)
	private String firstName;
	
	@Column(length=20, nullable=false)
	private String lastName;
	
	@Column(unique=true)
	private String email;
	
	private Date createAt;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", createAt=" + createAt + "]";
	}
}
