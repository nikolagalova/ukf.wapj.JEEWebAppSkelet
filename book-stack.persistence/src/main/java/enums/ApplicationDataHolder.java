package enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

import projectSI.persistence.dao.FakultaDAO;
import projectSI.persistence.dto.Department;
import projectSI.persistence.dto.Fakulta;

@Singleton
public class ApplicationDataHolder {
	
	private List<Fakulta> listFakult;
	private Map<Integer, Department> departmentsMap;
	
	@Inject
	private FakultaDAO fakultaDao;
	
	@PostConstruct
	private void init() {
		listFakult = fakultaDao.getAllFakulty();
		departmentsMap = new HashMap<Integer, Department>();
		for(Department d: fakultaDao.getAllDepartments()) {
			departmentsMap.put(d.getKod(), d);
		}
	}

	public List<Fakulta> getListFakult() {
		return listFakult;
	}

	public Map<Integer, Department> getDepartmentsMap() {
		return departmentsMap;
	}	
}
