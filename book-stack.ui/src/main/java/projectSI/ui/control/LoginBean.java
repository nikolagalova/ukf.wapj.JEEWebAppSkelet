package projectSI.ui.control;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import bookstack.business.AuthenticationService;
import projectSI.persistence.entities.Person;
import projectSI.ui.session.SessionBean;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = -8950642546222568421L;
	
	private Integer inputUserNumber;
	private String password;
	
	@Inject
	private SessionBean sessionBean;
	
	@Inject
	private AuthenticationService authenticationService;
	
	
	
	public String loginUser() {
		Person person = authenticationService.authenticateUser(inputUserNumber, password);
		if(person != null) {
			sessionBean.setPerson(person);
			return "publications.xhtml?faces-redirect=true";
		}
		else {
			FacesContext.getCurrentInstance().addMessage("loginMessages", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Chybné prihlásenie!", "Nesprávne prihlasovacie číslo."));
			return "#";
		}
	}
	
	public String logoutUser() {
		sessionBean.setPerson(null);
		return "home.xhtml?faces-redirect=true";
	}
	
	
	
	//GETTERS & SETTERS

	public Integer getInputUserNumber() {
		return inputUserNumber;
	}

	public void setInputUserNumber(Integer inputUserNumber) {
		this.inputUserNumber = inputUserNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
