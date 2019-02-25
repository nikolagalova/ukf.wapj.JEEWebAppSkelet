package projectSI.ui.control;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

import bookstack.business.PublicationChartDataService;
import projectSI.persistence.dto.PersonPublicationChartDTO;
import projectSI.persistence.entities.PubEpcSkupiny;
import projectSI.ui.session.SessionBean;

@ViewScoped
@Named
public class PublicationBean implements Serializable{

	private static final long serialVersionUID = -1204483474004129330L;
	
	private static final int NUMBER_OF_YEARS_IN_CHARTS = 6;
	
	private BarChartModel pubCountInYearsChartModel;
	private HorizontalBarChartModel pubRateInYearsChartModel;
	
	private Map<PubEpcSkupiny, List<PersonPublicationChartDTO>> pubChartData;
	private int beginChartYear;
	private int endChartYear;
		
	@Inject
	private SessionBean sessionBean;
	
	@Inject
	private PublicationChartDataService pubChartDataService;
	
	@PostConstruct
	private void init() {
		computeBoundaryChartYears();
		int ldapId = sessionBean.getPerson().getLdapId();
		pubChartData = pubChartDataService.getPublicationChartData(ldapId, beginChartYear, endChartYear);
		
		loadPubCountInYearsChartModel();
		loadPubRateInYearsChartModel();
	}
	
	private void computeBoundaryChartYears() {
		Calendar cal = Calendar.getInstance();
		endChartYear = cal.get(Calendar.YEAR);
		cal.add(Calendar.YEAR, -NUMBER_OF_YEARS_IN_CHARTS +1);
		beginChartYear = cal.get(Calendar.YEAR);
	}
	
	private ChartSeries getChartSerie(PubEpcSkupiny category) {
		ChartSeries series = new ChartSeries(category.getPopisSkupiny());
		
		return initializeSeries(series);
	}
	
	public ChartSeries initializeSeries(ChartSeries series) {
		for(int i = beginChartYear; i <= endChartYear; i++) {
			series.set(i, 0);
		}
		return series;
	}
	
	private void loadPubCountInYearsChartModel() {
		pubCountInYearsChartModel = new BarChartModel();
		for(PubEpcSkupiny c: pubChartData.keySet()){
			ChartSeries categorySeries = getChartSerie(c);
			for(PersonPublicationChartDTO data: pubChartData.get(c)) {
				categorySeries.set(data.getYear(), data.getCount());
			}
			pubCountInYearsChartModel.addSeries(categorySeries);
		}
		pubCountInYearsChartModel.setTitle("Počet publikácií za posledných 6 rokov");
		pubCountInYearsChartModel.setLegendPosition("ne");
		//pubCountInYearsChartModel.setStacked(true);
		pubCountInYearsChartModel.setSeriesColors(pubChartDataService.getCategoriesColorString(pubChartData.keySet()));
		pubCountInYearsChartModel.setDatatipFormat("%2$d");
		Axis xAxis = pubCountInYearsChartModel.getAxis(AxisType.X);
		xAxis.setLabel("Rok");
		//xAxis.setMax(2015); xAxis.setMin(2005);
		//xAxis.setTickCount(10);
		Axis yAxis = pubCountInYearsChartModel.getAxis(AxisType.Y);
		yAxis.setLabel("Počet");
		yAxis.setMin(0);
	}
	
	private void loadPubRateInYearsChartModel() {
		pubRateInYearsChartModel = new HorizontalBarChartModel();
		for(PubEpcSkupiny c: pubChartData.keySet()){
			ChartSeries categorySeries = getChartSerie(c);
			for(PersonPublicationChartDTO data: pubChartData.get(c)) {
				categorySeries.set(data.getYear(), data.getPodiel());
			}
			pubRateInYearsChartModel.addSeries(categorySeries);
		}
		pubRateInYearsChartModel.setTitle("Podiel na publikáciách za posledných 6 rokov");
		pubRateInYearsChartModel.setLegendPosition("ne");
		pubRateInYearsChartModel.setSeriesColors(pubChartDataService.getCategoriesColorString(pubChartData.keySet()));
		pubRateInYearsChartModel.setStacked(true);
		pubRateInYearsChartModel.setDatatipFormat("%s");
		Axis xAxis = pubRateInYearsChartModel.getAxis(AxisType.X);
		xAxis.setLabel("Podiel");
		//xAxis.setMax(2015); xAxis.setMin(2005);
		//xAxis.setTickCount(10);
		Axis yAxis = pubRateInYearsChartModel.getAxis(AxisType.Y);
		yAxis.setLabel("Rok");
		yAxis.setMin(0);
	}
	
	public boolean isChartDataEmpty() {
		return pubChartData.isEmpty();
	}
	
	/* GETTERS & SETTERS */

	public BarChartModel getPubCountInYearsChartModel() {
		return pubCountInYearsChartModel;
	}

	public void setPubCountInYearsChartModel(BarChartModel pubCountInYearsChartModel) {
		this.pubCountInYearsChartModel = pubCountInYearsChartModel;
	}

	public HorizontalBarChartModel getPubRateInYearsChartModel() {
		return pubRateInYearsChartModel;
	}

	public void setPubRateInYearsChartModel(HorizontalBarChartModel pubRateInYearsChartModel) {
		this.pubRateInYearsChartModel = pubRateInYearsChartModel;
	}
}
