package com.crud.model;
/**
 * @author V.Vishnu Priya on 08/02/2018
 * @version 1.0.0
 * <p>EmployeeEntity class which generates the entity bean for the table employee and maintains cardinality relationship
 * with the department table.</p>
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name="EMPLOYEE")
public class EmployeeEntity implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="EMP_ID")
	private Integer id;	
	@NotEmpty
	@Column(name="EMP_NAME")
	private String empName;
	@Column(name="EMP_ADDRESS")
	private String email;
	
	//Many Departments can be mapped to one employee hence one to many relationship.
	@NotNull
	@ManyToOne
	@JoinColumn(name = "EMP_DEPT_ID")
	private DepartmentEntity department;
	
	public EmployeeEntity() {}
	 
   //Constructor overloading or IOC
	public EmployeeEntity(String empName, DepartmentEntity department) {
        this.empName = empName;
        this.department = department;
    }
     
 
    public EmployeeEntity(String empName) {
        this.empName = empName;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "EmployeeVO [id=" + id + ", empName=" + empName
				+  ", email=" + email
				+ ", department=" + department + "]";
	}
}