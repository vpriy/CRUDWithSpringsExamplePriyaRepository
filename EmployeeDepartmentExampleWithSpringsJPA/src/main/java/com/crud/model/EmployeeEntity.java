package com.crud.model;
/**
 * @author V.Vishnu Priya on 08/02/2018
 * @version 1.0.0
 * <p>EmployeeEntity class which generates the entity bean for the table employee and maintains cardinality relationship
 * with the department table.</p>
 */
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name="employee")
public class EmployeeEntity implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Integer id;
	
	@NotEmpty
	private String firstName;
	private String lastName;
	private String email;
	
	//Many Departments can be mapped to one employee hence one to many relationship.
	@NotNull
	@ManyToOne
	private DepartmentEntity department;
	
	public EmployeeEntity() {}
	 
   //Constructor overloading or IOC
	public EmployeeEntity(String name, DepartmentEntity department) {
        this.firstName = name;
        this.department = department;
    }
     
 
    public EmployeeEntity(String name) {
        this.firstName = name;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "EmployeeVO [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", department=" + department + "]";
	}
}