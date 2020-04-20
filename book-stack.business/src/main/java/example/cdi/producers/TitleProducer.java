package example.cdi.producers;

import javax.enterprise.inject.Produces;

import example.cdi.qualifiers.RegularName;
import example.cdi.qualifiers.ScientistName;

public class TitleProducer {
	
	@Produces @ScientistName
	private String scientistTitle = "Sir";
	
	@Produces @RegularName
	private String regularTitle = "Mr.";

}
