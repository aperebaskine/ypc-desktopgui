package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.util.DialogUtils;
import com.pinguela.yourpc.desktop.view.SearchView;
import com.pinguela.yourpc.desktop.view.StatisticsSearchView;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.model.ProductStatistics;
import com.pinguela.yourpc.service.ProductStatisticsService;
import com.pinguela.yourpc.service.impl.ProductStatisticsServiceImpl;


@SuppressWarnings("serial")
public class StatisticsSearchAction 
extends SearchAction<Product> {
	
	private static Logger logger = LogManager.getLogger(StatisticsSearchAction.class);
	
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
	
	public StatisticsSearchAction(SearchView<Product> view) {
		super(view);
		statisticsService = new ProductStatisticsServiceImpl();
	}

	@Override
	protected TableModel fetchData() {
		ProductCriteria criteria = (ProductCriteria) getView().getCriteria();
		List<ProductStatistics> results = null;
		
		try {
			results = ((StatisticsSearchView) getView()).getMode() == 0
					? statisticsService.findMostSold(criteria.getLaunchDateMin(), criteria.getLaunchDateMax(),
							criteria.getCategoryId())
					: statisticsService.findMostReturned(criteria.getLaunchDateMin(), criteria.getLaunchDateMax(),
							criteria.getCategoryId());
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			DialogUtils.showDatabaseAccessErrorDialog((Component) getView());
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
