package com.crud.model;
/**
 * @author V.Vishnu Priya on 08/02/2018
 * @version 1.0.0
 * <p>DepartmentEntity class which generates the entity bean for the table employee and maintains cardinality relationship
 * with the employee table.</p>
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table (name="DEPARTMENT")
public class DepartmentEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="EMP_DEPT_ID")
	private Integer id;
	@Column(name="DEPARTMENTNAME")
	private String name;
	
	public DepartmentEntity(){
		
	}

	//Constructor overloading or IOC
	public DepartmentEntity(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	//One Department can be mapped to many employees hence one to many relationship.
	@OneToMany(mappedBy="DEPARTMENT",cascade=CascadeType.PERSIST)
    private List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
	
	public List<EmployeeEntity> getEmployees() {
        return employees;
    }
    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DepartmentVO [id=" + id + ", name=" + name + "]";
	}

}
