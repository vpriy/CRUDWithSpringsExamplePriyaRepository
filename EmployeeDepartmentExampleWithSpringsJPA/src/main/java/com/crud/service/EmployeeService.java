package com.crud.service;
/**
 * @author V.Vishnu Priya on 08/02/2018.
 * @version 1.0.0
 * An Interface layer or a class which has its method signature & perform transactions like Fetch,Add,Update,Delete operations.
 * Also it talks to the repository or DAO service layer.
 */
import java.util.List;

import com.crud.model.DepartmentEntity;
import com.crud.model.EmployeeEntity;


public interface EmployeeService 
{
	public List<EmployeeEntity> getAllEmployees();
	public List<DepartmentEntity> getAllDepartments();
	public void addEmployee(EmployeeEntity employee);
	public void deleteEmployee(EmployeeEntity employee);
}
