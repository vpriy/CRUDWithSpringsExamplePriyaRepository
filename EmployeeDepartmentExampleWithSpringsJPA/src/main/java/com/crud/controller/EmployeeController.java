package com.crud.controller;
/**
 * @author V.Vishnu Priya on 08/02/2018
 * @version 1.0.0
 * <p>EmployeeController class which is a controller class that contains the request mapping
 * to indicate which operations to be triggered from UI either insert or read or update or delete.
 * This controller layer is also binded with Jsp class like view controller binding wherein the variable updated here will be
 * reflected in the front end jsp form. The scenario is to perform the CRUD operations for 
 * employee and department tables having one to many and many to one relationships towards each other in the database.</p>
 */

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.crud.editors.DepartmentEditor;
import com.crud.model.DepartmentEntity;
import com.crud.model.EmployeeEntity;
import com.crud.service.EmployeeService;

@Controller
@RequestMapping("/employee-module")
@SessionAttributes("employee")
public class EmployeeController
{
	//Dependency injection of springs
	@Autowired
	EmployeeService employeeService;

	private Validator validator;

	//Bind custom validator for the employee form
	public EmployeeController()
	{
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	/**
	 * This method Binds DepartmentEditor to DepartmentEntity; Look at JSP file for clearer picture.
	 * @param binder
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DepartmentEntity.class, new DepartmentEditor());
    }
	
	/**
	 * This method Binds all departments in the department table.
	 * @return List of DepartmentEntity objects
	 */ 
	@ModelAttribute("allDepartments")
	public List<DepartmentEntity> populateDepartments() 
	{
		List<DepartmentEntity> departments = employeeService.getAllDepartments();
		return departments;
	}
	
	/**
	 * This method Binds all employees in the employee table.
	 * @return List of EmployeeEntity objects
	 */
	@ModelAttribute("allEmployees")
    public List<EmployeeEntity> populateEmployees() 
    {
        List<EmployeeEntity> employees = employeeService.getAllEmployees();
        return employees;
    }
	
	/**
	 * This method will be called in initial page load at GET /employee-module
	 * @return empty Employee form object
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) 
	{
		EmployeeEntity employeeVO = new EmployeeEntity();
		model.addAttribute("employee", employeeVO);
		return "listEmployeeView";
	}
	
	/**
	 * This method fetches the employees list from the employee table using GET operation
	 * @param model
	 * @return listEmployeeView string object.
	 */
	@RequestMapping(value = "/fetch" , method = RequestMethod.GET)
	public String getEmployeesList(Model model) 
	{
		model.addAttribute("employee", employeeService.getAllEmployees());
		return "listEmployeeView";
	}
	
	/**
	 * This method will be called on submitting add employee form POST /employee-module which inserts
	 * records in the employee table
	 * @param employeeVO object
	 * @return redirection to listEmployeeView form.
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute("employee") EmployeeEntity employeeVO,
			BindingResult result, SessionStatus status) {

		Set<ConstraintViolation<EmployeeEntity>> violations = validator.validate(employeeVO);
		
		for (ConstraintViolation<EmployeeEntity> violation : violations) 
		{
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("employee", propertyPath, "Invalid "+ propertyPath + "(" + message + ")"));
        }

		if (result.hasErrors()) {
			return "listEmployeeView";
		}
		// Store the employee information in database
		employeeService.addEmployee(employeeVO);
		
		// Mark Session Complete and redirect to URL so that page refresh do not re-submit the form
		status.setComplete();
		return "redirect:employee-module";
	}
	
	/**
	 * This method will be called on clicking on delete link form POST /employee-module which deletes
	 * records in the employee table
	 * @param employeeVO object
	 * @param id employeeid object.
	 * @return redirection to listEmployeeView form.
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteEmployee(@ModelAttribute("id") Integer id,
			BindingResult result, SessionStatus status) {
		EmployeeEntity employeeVO = new EmployeeEntity();
		DepartmentEntity departmentVO = new DepartmentEntity();
		departmentVO.setId(id);
		employeeVO.setDepartment(departmentVO);
		// deleted the employee information in database
		employeeService.deleteEmployee(employeeVO);

		// Mark Session Complete and redirect to URL so that page refresh do not re-submit the form
		status.setComplete();
		return "redirect:employee-module";
	}
	
	/**
	 * This method will be called on clicking on Edit link form POST /employee-module which edits/updates
	 * records in the employee table
	 * @param employeeVO object
	 * @param id employeeid object.
	 * @return redirection to listEmployeeView form.
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String editEmployee(@ModelAttribute("id") Integer id,
			BindingResult result, SessionStatus status, @ModelAttribute("employee") EmployeeEntity employeeTO) {
		EmployeeEntity employeeVO = new EmployeeEntity();
		DepartmentEntity departmentVO = new DepartmentEntity();
		departmentVO.setId(id);
		employeeVO.setDepartment(departmentVO);
		// deletes the employee information in database
		employeeService.deleteEmployee(employeeVO);

		// Store the employee information in database
		employeeService.addEmployee(employeeTO);
		
		// Mark Session Complete and redirect to URL so that page refresh do not re-submit the form
		status.setComplete();
		return "redirect:employee-module";
	}

}