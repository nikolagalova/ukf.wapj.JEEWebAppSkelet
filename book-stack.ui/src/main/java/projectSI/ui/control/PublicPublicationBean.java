package projectSI.ui.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;

import bookstack.business.FakultyPublicationChartDataService;
import enums.ApplicationDataHolder;
import projectSI.persistence.dto.Department;
import projectSI.persistence.dto.Fakulta;
import projectSI.persistence.dto.PublicPublicationChartDTO;

@ViewScoped
@Named
public class PublicPublicationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5833894765758825424L;

	private List<Fakulta> allFakulty;
	//TODO: implement converter
	private Fakulta selectedFakulta;
	
	private BarChartModel publicationsInFakultyChartModel;
	private List<PublicPublicationChartDTO> fakultyChartData;
	private ArrayList<Integer> indexKeyMapper = new ArrayList<>();
	
	@Inject
	private ApplicationDataHolder applicationData;
	
	@Inject
	private FakultyPublicationChartDataService fakultyPubChartDataService;

	@PostConstruct
	private void init()  {
		allFakulty = applicationData.getListFakult();
		if (allFakulty != null && !allFakulty.isEmpty()) {
			selectedFakulta = allFakulty.get(0);
			fakultyChartData = fakultyPubChartDataService.getFakultyPublicationChartData(selectedFakulta.getKod());
			loadPublicationsInFakultyChartModel();
		}
	}
	
	private BarChartModel createBasicBarChart() {
		BarChartModel chartModel = new HorizontalBarChartModel();
		chartModel.setAnimate(true);
		chartModel.setDatatipFormat("%1$d");
		chartModel.setMouseoverHighlight(false);
		chartModel.setZoom(false);
		chartModel.setLegendPosition("e");
		chartModel.setBarWidth(15);
		
		Axis xAxis = chartModel.getAxis(AxisType.X);
		xAxis.setMin(0);
		xAxis.setLabel("Početnosť");
		return chartModel;
	}
	
	private void loadPublicationsInFakultyChartModel() {
		publicationsInFakultyChartModel = createBasicBarChart();
		LineChartSeries rateDataSeries = new LineChartSeries("Podiel na publikáciách");
		ChartSeries countDataSeries = new ChartSeries("Počet publikácií");
		for(PublicPublicationChartDTO data: fakultyChartData) {
			Department department = applicationData.getDepartmentsMap().get(data.getKatedraKod());
			if (department != null) {
				countDataSeries.set(department, data.getCount());
				rateDataSeries.set(department, data.getPodiel());
				indexKeyMapper.add(department.getKod());
			}
			else {
				//TODO: log error (unknown department)
			}
		}
		
		rateDataSeries.setYaxis(AxisType.Y);
		rateDataSeries.setXaxis(AxisType.X2);

		publicationsInFakultyChartModel.addSeries(countDataSeries);
		publicationsInFakultyChartModel.addSeries(rateDataSeries);
		
		publicationsInFakultyChartModel.getAxes().put(AxisType.X2, new LinearAxis("Podiel"));
		Axis x2Axis = publicationsInFakultyChartModel.getAxis(AxisType.X2);
		x2Axis.setMin(0);
	}
	
	public void selectDepartment(ItemSelectEvent event) throws IOException {
		int selectedCode = indexKeyMapper.get(event.getItemIndex());
		FacesContext.getCurrentInstance().getExternalContext().redirect("departmentPublications.xhtml?faces-redirect=true&departmentCode="+selectedCode);
	}
	
	/* GETTERS & SETTERS */
	
	public List<Fakulta> getAllFakulty() {
		return allFakulty;
	}
	public void setAllFakulty(List<Fakulta> allFakulty) {
		this.allFakulty = allFakulty;
	}

	public BarChartModel getPublicationsInFakultyChartModel() {
		return publicationsInFakultyChartModel;
	}

	public void setPublicationsInFakultyChartModel(
			BarChartModel publicationsInFakultyChartModel) {
		this.publicationsInFakultyChartModel = publicationsInFakultyChartModel;
	}

}
