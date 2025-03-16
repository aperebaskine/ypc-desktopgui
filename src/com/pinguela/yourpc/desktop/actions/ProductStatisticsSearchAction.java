package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.util.DialogUtils;
import com.pinguela.yourpc.desktop.view.AbstractSearchView;
import com.pinguela.yourpc.model.ProductStatistics;
import com.pinguela.yourpc.model.ProductStatisticsCriteria;
import com.pinguela.yourpc.service.ProductStatisticsService;
import com.pinguela.yourpc.service.impl.ProductStatisticsServiceImpl;


@SuppressWarnings("serial")
public class ProductStatisticsSearchAction 
extends SearchAction<ProductStatistics> {
	
	private static Logger logger = LogManager.getLogger(ProductStatisticsSearchAction.class);
	
	private static final String[] COLUMN_NAMES = {
		"ID",
		"Name",
		"Sold",
		"Returned",
		"Return %",
		"Average sale price",
		"Average purchase price"
	};
	
	private ProductStatisticsService statisticsService;
	
	public ProductStatisticsSearchAction(AbstractSearchView<ProductStatistics> view) {
		super(view);
		statisticsService = new ProductStatisticsServiceImpl();
	}

	@Override
	protected TableModel fetchData() {
		ProductStatisticsCriteria criteria = (ProductStatisticsCriteria) getView().getCriteria();
		List<ProductStatistics> results = null;
		
//		try {
//			results = statisticsService.getSalesStatistics(criteria);
//		} catch (YPCException e) {
//			logger.error(e.getMessage(), e);
//			DialogUtils.showDatabaseAccessErrorDialog((Component) getView());
//		}
		
		if (results == null) {
			return null;
		}
		
		return new DefaultTableModel(toArray(results), COLUMN_NAMES);
	}
	
	protected Object[][] toArray(List<ProductStatistics> results) {
		
		if (results == null) {
			throw new IllegalArgumentException("Results parameter cannot be null.");
		}
		
		Object[][] array = new Object[results.size()][COLUMN_NAMES.length];
		
		for (int i = 0; i < array.length; i++) {
			int j = 0;
			array[i][j++] = results.get(i).getProductId();
			array[i][j++] = results.get(i).getProductName();
			array[i][j++] = results.get(i).getQuantitySold();
			array[i][j++] = results.get(i).getQuantityReturned();
			array[i][j++] = results.get(i).getPctReturned();
			array[i][j++] = results.get(i).getAvgPurchasePrice();
			array[i][j++] = results.get(i).getAvgSalePrice();
		}
		
		return array;
	}

}
