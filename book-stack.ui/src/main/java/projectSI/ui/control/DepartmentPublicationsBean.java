package projectSI.ui.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import enums.ApplicationDataHolder;
import projectSI.persistence.dao.PersonDAO;
import projectSI.persistence.dto.Department;
import projectSI.persistence.entities.PubPublikacie;

@ViewScoped
@Named
public class DepartmentPublicationsBean implements Serializable{

	private static final long serialVersionUID = -484946387894073212L;
	
	private List<PubPublikacie> allDepartmentPublications;
	private Department department;

	@Inject
	private PersonDAO personDAO;
	
	@Inject
	private ApplicationDataHolder applicationData;
	
	@PostConstruct
	private void init() {
		String codeParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("departmentCode");
		int code = -1;
		try {
			code = Integer.parseInt(codeParam);
		} catch (Exception e) {
			// TODO: handle exception
		}
		department = (applicationData.getDepartmentsMap().get(code));
		allDepartmentPublications = new ArrayList<>();
		allDepartmentPublications.addAll(personDAO.getDepartmentPublications(code));
	}
	

	public String getBooleanIcon(String value) {
		if (value.trim().equals("")) return "icon-minus";
		else return "icon-ok icon-large green-icon";
	}


	public List<PubPublikacie> getAllDepartmentPublications() {
		return allDepartmentPublications;
	}


	public void setAllDepartmentPublications(
			List<PubPublikacie> allDepartmentPublications) {
		this.allDepartmentPublications = allDepartmentPublications;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}

}
