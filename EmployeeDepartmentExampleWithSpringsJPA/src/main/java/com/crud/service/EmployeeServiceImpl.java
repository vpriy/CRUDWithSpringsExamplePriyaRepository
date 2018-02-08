package com.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.dao.EmployeeDAO;
import com.crud.model.DepartmentEntity;
import com.crud.model.EmployeeEntity;
/**
 * @author V.Vishnu Priya on 08/02/2018.
 * @version 1.0.0
 * A service class which implements the EmployeeService interface transactions like Fetch,Add,Update,Delete operations.
 * Also it talks to the repository or DAO service layer.
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	//Fetches all employees list from the database
	public List<EmployeeEntity> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	//Fetches all departments list from the database
	public List<DepartmentEntity> getAllDepartments() {
		return employeeDAO.getAllDepartments();
	}

	//Inserts the employee details to the database
	public void addEmployee(EmployeeEntity employee) {
		employeeDAO.addEmployee(employee);
	}
	
	//Deletes the employees based on the employeeid from the database
	public void deleteEmployee(EmployeeEntity employee) {
		employeeDAO.deleteEmployee(employee);
	}
}
