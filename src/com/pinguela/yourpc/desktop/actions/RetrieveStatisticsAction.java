package com.pinguela.yourpc.desktop.actions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.desktop.view.StatisticsView;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.model.ProductStatisticsDTO;
import com.pinguela.yourpc.service.ProductStatisticsService;
import com.pinguela.yourpc.service.impl.ProductStatisticsServiceImpl;

@SuppressWarnings("serial")
public class RetrieveStatisticsAction 
extends YPCAction {
	
	private static Logger logger = LogManager.getLogger(RetrieveStatisticsAction.class);
	
	private ProductStatisticsService service;
	
	private StatisticsView view;
	
	public RetrieveStatisticsAction(StatisticsView view) {
		super("Search...", Icons.SEARCH_ICON);
		this.view = view;
		this.service = new ProductStatisticsServiceImpl();
	}

	@Override
	protected void doAction() {
		ProductCriteria criteria = view.getCriteria();
		
		try {
			List<ProductStatisticsDTO> results = service.findByProduct(criteria.getLaunchDateMin(), criteria.getLaunchDateMax(), criteria.getId());
			view.showChart(ChartFactory.createLineChart("Product statistics", "Date", "Price / Quantity", createDataset(results), PlotOrientation.VERTICAL, true, true, false));
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			SwingUtils.showDatabaseAccessErrorDialog(view);
		} 
	}
	
	private CategoryDataset createDataset(List<ProductStatisticsDTO> results) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (ProductStatisticsDTO dto : results) {
			dataset.addValue(dto.getQuantitySold(), "Quantity", dto.getDate());
			dataset.addValue(dto.getAvgPrice(), "Price", dto.getDate());
		}
		
		return dataset;
	}

}
