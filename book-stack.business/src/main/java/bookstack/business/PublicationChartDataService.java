package bookstack.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import projectSI.persistence.dao.CategoryDAO;
import projectSI.persistence.dao.PersonDAO;
import projectSI.persistence.dto.PersonPublicationChartDTO;
import projectSI.persistence.entities.PubEpcSkupiny;

@Stateless
public class PublicationChartDataService {
	
	@Inject
	private PersonDAO personDAO;
	
	@Inject
	private CategoryDAO categoryDAO;
	
	public Map<PubEpcSkupiny, List<PersonPublicationChartDTO>> getPublicationChartData(int ldapId, int beginYear, int endYear){
		Map<PubEpcSkupiny, List<PersonPublicationChartDTO>> result = new HashMap<>();
		List<PubEpcSkupiny> allCategories = new ArrayList<>();
		List<PersonPublicationChartDTO> allPublications = new ArrayList<>();
		
		allPublications.addAll(personDAO.getPersonPublicationData(ldapId, beginYear, endYear));
		allCategories.addAll(categoryDAO.getAllCategories());
		for(PubEpcSkupiny c: allCategories) {
			List<PersonPublicationChartDTO> categoryPubList = new ArrayList<>();
			for (PersonPublicationChartDTO pubData: allPublications) {
				//if current pubData has current category of iteration
				if(pubData.getPubCategory().getIdSkupiny().equals(c.getIdSkupiny())){
					categoryPubList.add(pubData);
				}
			}
			if(!categoryPubList.isEmpty()){
				result.put(c, categoryPubList);
			}
		}
		return result;
	}
	
	//TODO: add color codes to property files in ui and move this methods to separated class into iu package
	private String getCategoryColor(PubEpcSkupiny category){
		switch (category.getIdSkupiny()) {
		case "A1":
			return "FFC400";
		case "A2":
			return "D64D8B";
		case "B1":
			return "87DB00";
		case "B2":
			return "00D1CA";
		case "C":
			return "4388CC";

		default:
			return "E63737";
		}
	}
	
	public String getCategoriesColorString(Set<PubEpcSkupiny> categories) {
		StringBuilder sb = new StringBuilder();
		for(PubEpcSkupiny c: categories){
			sb.append(getCategoryColor(c));
			sb.append(",");
		}
		if(sb.length()>0) sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

}
