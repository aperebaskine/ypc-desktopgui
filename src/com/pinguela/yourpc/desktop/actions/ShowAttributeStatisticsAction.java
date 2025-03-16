package com.pinguela.yourpc.desktop.actions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.util.DialogUtils;
import com.pinguela.yourpc.desktop.view.AttributeStatisticsView;
import com.pinguela.yourpc.model.AttributeStatistics;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.service.ProductStatisticsService;
import com.pinguela.yourpc.service.impl.ProductStatisticsServiceImpl;

@SuppressWarnings("serial")
public class ShowAttributeStatisticsAction 
extends YPCAction {
	
	private static Logger logger = LogManager.getLogger(ShowAttributeStatisticsAction.class);

	private AttributeStatisticsView view;
	
	private ProductStatisticsService statisticsService;
	
	public ShowAttributeStatisticsAction(AttributeStatisticsView view) {
		super("Search...");
		this.view = view;
		this.statisticsService = new ProductStatisticsServiceImpl();
	}
	
	@Override
	protected void doAction() {
		ProductCriteria criteria = view.getCriteria();
		List<AttributeStatistics<?>> results = null;
		
//		try {
//			results = statisticsService.findByAttribute(criteria.getLaunchDateMin(), criteria.getLaunchDateMax(),
//					criteria.getCategoryId(), criteria.getAttributes().iterator().next());
//		} catch (YPCException e) {
//			logger.error(e.getMessage(), e);
//			DialogUtils.showDatabaseAccessErrorDialog(view);
//		}
		
		if (results == null) {
			return;
		}
		
		PieDataset<?> dataset = toDataset(results);
		
		JFreeChart chart = ChartFactory.createPieChart("Sales by value", dataset, true, true, false);
		
		view.showChart(chart);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	protected static PieDataset toDataset(List<AttributeStatistics<?>> results) {
		DefaultPieDataset dataset = new DefaultPieDataset<>();
		
		for (int i = 0; i < results.size(); i++) {
			AttributeStatistics result = results.get(i);
			dataset.insertValue(i, (Comparable) result.getValue(), result.getQuantity());
		}
		
		for (AttributeStatistics result : results) {
			dataset.setValue((Comparable) result.getValue(), result.getQuantity());
		}
		
		return dataset;
	}

}
