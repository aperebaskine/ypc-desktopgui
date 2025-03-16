package com.pinguela.yourpc.desktop.actions;

import com.pinguela.DataException;
import com.pinguela.ServiceException;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.AbstractSearchView;
import com.pinguela.yourpc.model.dto.LocalizedProductDTO;
import com.pinguela.yourpc.service.ProductService;
import com.pinguela.yourpc.service.impl.ProductServiceImpl;

@SuppressWarnings("serial")
public class DeleteProductAction
extends DeleteItemAction<LocalizedProductDTO> {

	private ProductService productService;

	{
		productService = new ProductServiceImpl();
	}

	public DeleteProductAction(EntityView<LocalizedProductDTO> source) {
		super(source);
	}

	public DeleteProductAction(AbstractSearchView<LocalizedProductDTO> source) {
		super(source);
	}
	
	@Override
	protected String getItemName(LocalizedProductDTO item) {
		return item.getName();
	}

	@Override
	protected boolean shouldDeleteFromDatabase() {
		return true;
	}

	@Override
	protected void deleteFromDatabase(LocalizedProductDTO item) throws ServiceException, DataException {
		productService.delete(item.getId());
	}

}
