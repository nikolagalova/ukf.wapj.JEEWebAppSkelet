package bookstack.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import projectSI.persistence.dao.FakultaDAO;
import projectSI.persistence.dto.PublicPublicationChartDTO;

@Stateless
public class FakultyPublicationChartDataService {
	
	@Inject
	private FakultaDAO fakultaDAO;
	
	public List<PublicPublicationChartDTO> getFakultyPublicationChartData(int fakultaKod) {
		List<PublicPublicationChartDTO> result = new ArrayList<>();
		result.addAll(fakultaDAO.getFakultyPublications(fakultaKod));
		return result;
	}
}
