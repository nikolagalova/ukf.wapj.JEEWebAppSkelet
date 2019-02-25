package projectSI.ui.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projectSI.persistence.dao.PersonDAO;
import projectSI.persistence.entities.Person;
import projectSI.persistence.entities.PubPublikacie;
import projectSI.ui.session.SessionBean;

@ViewScoped
@Named
public class EmployeePublicationsBean implements Serializable{

	private static final long serialVersionUID = -484946387894073212L;
	
	private List<PubPublikacie> allEmpPublications;
	private Person employee;
		
	@Inject
	private PersonDAO personDAO;
	
	@PostConstruct
	private void init() {
		//read request param of selected person
		String personLdap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("personLdap");
		int ldap = -1;
		try {
			ldap = Integer.parseInt(personLdap);
		} catch (Exception e) {
			// TODO: handle exception
		}
		allEmpPublications = new ArrayList<>();
		allEmpPublications.addAll(personDAO.getPersonPublications(ldap));
		employee = personDAO.getPersonByLadapId(ldap);
	}
	

	public String getBooleanIcon(String value) {
		if (value.trim().equals("")) return "icon-minus";
		else return "icon-ok icon-large green-icon";
	}

	public List<PubPublikacie> getAllEmpPublications() {
		return allEmpPublications;
	}

	public void setAllEmpPublications(List<PubPublikacie> allUserPublications) {
		this.allEmpPublications = allUserPublications;
	}


	public Person getEmployee() {
		return employee;
	}


	public void setEmployee(Person employee) {
		this.employee = employee;
	}

}
