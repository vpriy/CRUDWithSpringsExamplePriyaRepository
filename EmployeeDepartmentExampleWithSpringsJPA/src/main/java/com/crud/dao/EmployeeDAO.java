package com.crud.dao;
/**
 * @author V.Vishnu Priya on 08/02/2018
 * @version 1.0.0
 * <p>EmployeeDAO class which is an interface class for EmployeeDAOServiceImpl and it contains 
 * the method signatures or the interface methods for the CRUD operations to be performed in the database.</p>
 */
import java.util.List;

import com.crud.model.DepartmentEntity;
import com.crud.model.EmployeeEntity;

public interface EmployeeDAO 
{
	public List<EmployeeEntity> getAllEmployees();
	public List<DepartmentEntity> getAllDepartments();
	public void addEmployee(EmployeeEntity employee);
	public void deleteEmployee(EmployeeEntity employee);
}