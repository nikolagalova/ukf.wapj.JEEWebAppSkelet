package projectSI.ui.session;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import enums.Constants;
import projectSI.persistence.entities.Person;

@Named
@SessionScoped
public class SessionBean implements Serializable{

	private static final long serialVersionUID = 4223612726427997449L;
	
	private Person person;
	
	public void checkUser() {
		if(person == null)
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public boolean isUserLoggedIn() {
		return person != null;
	}
	public boolean isHeadOfDepartment() {
		return person != null && Constants.HEAD_OF_DEPARTMENT_CODE.equals(person.getKzamKod());
	}

	
	//GETTERS & SETTERS
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	

}
