package com.crud.dao;
/**
 * @author V.Vishnu Priya on 08/02/2018
 * @version 1.0.0
 * <p>EmployeeDAOImpl class which is an implementation class for EmployeeDAO service and it talks to the
 * java persistence layer using Entitymanager to perform the CRUD operations to/from the database.</p>
 */
import java.util.List;
import org.slf4j.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crud.model.DepartmentEntity;
import com.crud.model.EmployeeEntity;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO 
{
	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDAOImpl.class);
    
	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * This method fetches all the employees list of records from employee table from the database.
	 */
	public List<EmployeeEntity> getAllEmployees() 
	{
		List<EmployeeEntity> employees = null;
		try {
			employees = manager.createQuery("Select a From EmployeeEntity a", EmployeeEntity.class).getResultList();
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Error in fetching the employees list from the database - getAllEmployees() of EmployeeDAOImpl class", ex);
		}
		return employees;
	}
	
	/**
	 * This method fetches all the departments list of records from department table from the database.
	 */
	public List<DepartmentEntity> getAllDepartments() 
	{
		List<DepartmentEntity> depts = null;
		try {
			depts = manager.createQuery("Select a From DepartmentEntity a", DepartmentEntity.class).getResultList();
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Error in fetching the department list from the database - getAllDepartments() of EmployeeDAOImpl class", ex);
		}
		return depts;
	}
	
	/**
	 * This method fetches all the departments list of records based on the employee id from department table in the database.
	 */
	public DepartmentEntity getDepartmentById(Integer id) 
	{
		try {
			manager.find(DepartmentEntity.class, id);
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Error in fetching the department by department id from the database - getDepartmentById() of EmployeeDAOImpl class", ex);
		}
		return manager.find(DepartmentEntity.class, id);
	}
	
	/**
	 * This method inserts all the employee records into the employee table in the database.
	 * @param employee as an object which will persist.
	 */
	public void addEmployee(EmployeeEntity employee) 
	{
		try {
			if(null != employee) {
				employee.setDepartment(getDepartmentById(employee.getDepartment().getId()));
				manager.persist(employee);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Error in inserting the employee records into the database - addEmployee() of EmployeeDAOImpl class", ex);
		}
	}
	
	/**
	 * This method deletes all the employee records from employee table in the database.
	 * @param employee as an object which will be removed
	 */
	public void deleteEmployee(EmployeeEntity employee) 
	{
		try {
			if(null != employee) {
				employee.setDepartment(getDepartmentById(employee.getDepartment().getId()));
				manager.remove(employee);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Error in deleting the employee records from the database - deleteEmployee() of EmployeeDAOImpl class", ex);
		}
	}
	
}