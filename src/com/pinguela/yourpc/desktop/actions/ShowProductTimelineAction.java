package com.pinguela.yourpc.desktop.actions;

import java.awt.Color;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.util.DialogUtils;
import com.pinguela.yourpc.desktop.view.ProductTimelineView;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.model.ProductStatistics;
import com.pinguela.yourpc.service.ProductStatisticsService;
import com.pinguela.yourpc.service.impl.ProductStatisticsServiceImpl;

@SuppressWarnings("serial")
public class ShowProductTimelineAction 
extends YPCAction {
	
	private static Logger logger = LogManager.getLogger(ShowProductTimelineAction.class);
	
	private ProductStatisticsService service;
	
	private ProductTimelineView view;
	
	public ShowProductTimelineAction(ProductTimelineView view) {
		super("Search...", Icons.SEARCH_ICON);
		this.view = view;
		this.service = new ProductStatisticsServiceImpl();
	}

	@Override
	protected void doAction() {
		ProductCriteria criteria = view.getCriteria();
		
		try {
			List<ProductStatistics> results = service.findByProduct(criteria.getLaunchDateMin(), criteria.getLaunchDateMax(), criteria.getId());
			
			// TODO: Use XYDataset
			CategoryDataset[] datasets = createDatasets(results);
			CategoryDataset quantityDataset = datasets[0];
			CategoryDataset priceDataset = datasets[1];
			
			JFreeChart chart = ChartFactory.createLineChart("Product statistics", "Date", "Price", priceDataset, PlotOrientation.VERTICAL, true, true, false);
			
			CategoryPlot plot = chart.getCategoryPlot();
			NumberAxis quantityAxis = new NumberAxis("Quantity");
			plot.setRangeAxis(1, quantityAxis);
			
			LineAndShapeRenderer renderer = new LineAndShapeRenderer();
			renderer.setSeriesPaint(1, Color.BLUE);
			
			plot.setRenderer(1, renderer);
			
			plot.setDataset(1, quantityDataset);
			plot.mapDatasetToRangeAxis(1, 1);
			
			view.showChart(chart);
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			DialogUtils.showDatabaseAccessErrorDialog(view);
		} 
	}
	
	private CategoryDataset[] createDatasets(List<ProductStatistics> results) {
		DefaultCategoryDataset quantityDataset = new DefaultCategoryDataset();
		DefaultCategoryDataset priceDataset = new DefaultCategoryDataset();
		
		for (ProductStatistics dto : results) {
			quantityDataset.addValue(dto.getQuantitySold(), "Quantity", dto.getDate());
			
			priceDataset.addValue(dto.getAvgSalePrice(), "Price", dto.getDate());
		}
		
		return new CategoryDataset[] {quantityDataset, priceDataset};
	}

}
