package projectSI.ui.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projectSI.persistence.dao.PersonDAO;
import projectSI.persistence.entities.PubPublikacie;
import projectSI.ui.session.SessionBean;

@ViewScoped
@Named
public class PublicationListBean implements Serializable{

	private static final long serialVersionUID = -484946387894073212L;
	
	private List<PubPublikacie> allUserPublications;
	
	@Inject
	private SessionBean sessionBean;
	
	@Inject
	private PersonDAO personDAO;
	
	@PostConstruct
	private void init() {
		int ldapId = sessionBean.getPerson().getLdapId();
		allUserPublications = new ArrayList<>();
		allUserPublications.addAll(personDAO.getPersonPublications(ldapId));
	}
	

	public String getBooleanIcon(String value) {
		if (value.trim().equals("")) return "icon-minus";
		else return "icon-ok icon-large green-icon";
	}

	public List<PubPublikacie> getAllUserPublications() {
		return allUserPublications;
	}

	public void setAllUserPublications(List<PubPublikacie> allUserPublications) {
		this.allUserPublications = allUserPublications;
	}

}
