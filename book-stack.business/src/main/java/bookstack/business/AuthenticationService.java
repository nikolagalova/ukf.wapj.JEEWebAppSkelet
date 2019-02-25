package bookstack.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import projectSI.persistence.dao.PersonDAO;
import projectSI.persistence.entities.Person;

/**
 * Class for authentication services
 * @author Nikola
 *
 */
@Stateless
public class AuthenticationService {
	
	@Inject
	private PersonDAO personDAO;
	
	public Person authenticateUser(Integer userNumber, String password) {
		if(userNumber == null) return null;
		Person person = personDAO.getPersonByLadapId(userNumber);
		return person;
	}
}
