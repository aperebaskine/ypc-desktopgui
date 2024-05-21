package com.pinguela.yourpc.desktop.actions;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.ProductItemView;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.service.ImageFileService;
import com.pinguela.yourpc.service.ProductService;
import com.pinguela.yourpc.service.impl.ImageFileServiceImpl;
import com.pinguela.yourpc.service.impl.ProductServiceImpl;

public class SaveProductAction 
extends SaveItemAction<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7296590252331794074L;

	private ProductService productService;
	private ImageFileService imageFileService;

	public SaveProductAction(ProductItemView view) {
		super(view, "Save", Icons.OK_ICON);
		this.productService = new ProductServiceImpl();
		this.imageFileService = new ImageFileServiceImpl();
	}

	@Override
	protected void doSave() throws YPCException {
		Product editedProduct = getView().getModifiedItem();
		productService.update(editedProduct);
		getView().setItem(editedProduct);
		imageFileService.update(ImageFileService.PRODUCT_TYPE,
				getView().getItem().getId(), ((ProductItemView) getView()).getModifiedImageEntries());
	}

}
