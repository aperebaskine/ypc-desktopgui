package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ItemListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.ProductTableConstants;
import com.pinguela.yourpc.desktop.model.ActionPaneListTableModel;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.desktop.view.SearchView;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.model.Results;
import com.pinguela.yourpc.service.ProductService;
import com.pinguela.yourpc.service.impl.ProductServiceImpl;

public class ProductSearchAction extends SearchAction<Product>
implements ItemListener, ChangeListener, DocumentListener, TableModelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6271816817279591618L;

	private static Logger logger = LogManager.getLogger(ProductSearchAction.class);

	private ProductService productService;

	public ProductSearchAction(SearchView<Product> view) {
		super(view);
		productService = new ProductServiceImpl();
	}

	@Override
	protected TableModel getModel() {

		ProductSearchView view = (ProductSearchView) getView();
		ProductCriteria criteria = view.getCriteria();
		Results<Product> results = null;

		try {
			if (criteria.getId() != null) {
				results = Results.singleEntry(productService.findById(criteria.getId()));
			} else {
				results = productService.findBy(criteria, view.getPos(), view.getPageSize());
			}
		} catch (YPCException ypce) {
			logger.error(ypce.getMessage(), ypce);
		}

		return getTableModel(results);
	}

	protected TableModel getTableModel(Results<Product> results) {
		((ProductSearchView) getView()).setResultCount(results.getResultCount());
		return new ActionPaneListTableModel<Product>(ProductTableConstants.COLUMN_NAMES, results.getPage());
	}

}
