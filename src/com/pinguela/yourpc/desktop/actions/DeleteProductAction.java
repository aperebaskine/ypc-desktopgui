package com.pinguela.yourpc.desktop.actions;

import com.pinguela.DataException;
import com.pinguela.ServiceException;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.SearchView;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.service.ProductService;
import com.pinguela.yourpc.service.impl.ProductServiceImpl;

public class DeleteProductAction extends DeleteItemAction<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9082398589000416327L;

	private ProductService productService;

	{
		productService = new ProductServiceImpl();
	}

	public DeleteProductAction(EntityView<Product> source) {
		super(source);
	}

	public DeleteProductAction(SearchView<Product> source) {
		super(source);
	}
	
	@Override
	protected String getItemName(Product item) {
		return item.getName();
	}

	@Override
	protected boolean shouldDeleteFromDatabase() {
		return true;
	}

	@Override
	protected void deleteFromDatabase(Product item) throws ServiceException, DataException {
		productService.delete(item.getId());
	}

}
