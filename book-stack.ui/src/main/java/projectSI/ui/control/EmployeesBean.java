package projectSI.ui.control;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projectSI.persistence.dao.PersonDAO;
import projectSI.persistence.dto.PersonComparisionDTO;
import projectSI.ui.session.SessionBean;

@ViewScoped
@Named
public class EmployeesBean implements Serializable {

	private static final long serialVersionUID = 1026589204060242599L;
	
	private List<PersonComparisionDTO> personDataList;
	
	@Inject
	SessionBean sessionBean;
	
	@Inject
	PersonDAO personDAO;
	
	@PostConstruct
	private void init() {
		setPersonDataList(personDAO.getDepartmentEmploees(sessionBean.getPerson().getKatedraKod(), 
				sessionBean.getPerson().getLdapId()));
	}
	
	public String showEmployeesPublications(int ldapId) {
		return "employeePublications.xhtml?faces-redirect=true&personLdap="+ldapId;
	}
	
	/* GETTERS & SETTERS */ 

	public List<PersonComparisionDTO> getPersonDataList() {
		return personDataList;
	}

	public void setPersonDataList(List<PersonComparisionDTO> personDataList) {
		this.personDataList = personDataList;
	}

}
