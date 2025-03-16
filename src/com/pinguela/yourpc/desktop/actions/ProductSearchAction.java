package com.pinguela.yourpc.desktop.actions;

import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.ProductTableConstants;
import com.pinguela.yourpc.desktop.model.ListTableModel;
import com.pinguela.yourpc.desktop.util.LocaleUtils;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.desktop.view.AbstractSearchView;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.model.Results;
import com.pinguela.yourpc.model.dto.LocalizedProductDTO;
import com.pinguela.yourpc.service.ProductService;
import com.pinguela.yourpc.service.impl.ProductServiceImpl;

@SuppressWarnings("serial")
public class ProductSearchAction
extends SearchAction<LocalizedProductDTO> {

	private static Logger logger = LogManager.getLogger(ProductSearchAction.class);

	private ProductService productService;

	public ProductSearchAction(AbstractSearchView<LocalizedProductDTO> view) {
		super(view);
		productService = new ProductServiceImpl();
	}

	@Override
	protected TableModel fetchData() {

		ProductSearchView view = (ProductSearchView) getView();
		ProductCriteria criteria = (ProductCriteria) view.getCriteria();
		Results<LocalizedProductDTO> results = null;

		try {
			if (criteria.getId() != null) {
				results = Results.singleEntry(productService.findByIdLocalized(criteria.getId(), LocaleUtils.getLocale()));
			} else {
				results = productService.findBy(criteria, LocaleUtils.getLocale(), view.getPos(), view.getPageSize());
			}
		} catch (YPCException ypce) {
			logger.error(ypce.getMessage(), ypce);
		}

		return getTableModel(results);
	}

	protected TableModel getTableModel(Results<LocalizedProductDTO> results) {
		((ProductSearchView) getView()).setResultCount(results.getResultCount());
		return new ListTableModel<LocalizedProductDTO>(ProductTableConstants.COLUMN_NAMES, results.getPage());
	}

}
