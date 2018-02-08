package com.crud.editors;
/**
 * @author V.Vishnu Priya on 08/02/2018
 * @version 1.0.0
 * <p>DepartmentEditor class which binds the DepartmentEntity bean using entitybinder and sets the department name based on the 
 * employee id.</p>
 */
import java.beans.PropertyEditorSupport;

import com.crud.model.DepartmentEntity;


public class DepartmentEditor extends PropertyEditorSupport 
{
    @Override
    public void setAsText(String id) 
    {
    	DepartmentEntity d;
    	switch(Integer.parseInt(id))
		{
			case 1: d = new DepartmentEntity(1,  "Human Resource"); break;
			case 2: d = new DepartmentEntity(2,  "Finance"); break;
			case 3: d = new DepartmentEntity(3,  "Information Technology"); break;
			case 4: d = new DepartmentEntity(3,  "Operations"); break;
			default: d = null;
		}
        this.setValue(d);
    }
}
