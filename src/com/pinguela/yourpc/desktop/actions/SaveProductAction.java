package com.pinguela.yourpc.desktop.actions;

import javax.swing.Action;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.ProductView;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.service.ImageFileService;
import com.pinguela.yourpc.service.ProductService;
import com.pinguela.yourpc.service.impl.ImageFileServiceImpl;
import com.pinguela.yourpc.service.impl.ProductServiceImpl;

@SuppressWarnings("serial")
public class SaveProductAction 
extends SaveItemAction<Product> {

	private ProductService productService;
	private ImageFileService imageFileService;

	public SaveProductAction(ProductView view) {
		super(view, "Save", Icons.OK_ICON);
		this.productService = new ProductServiceImpl();
		this.imageFileService = new ImageFileServiceImpl();
	}
	
	@Override
	protected void doCreate(Product item) throws YPCException {
		productService.create(item);
		getView().setEntity(productService.findById(item.getId()));
		saveImages(item);
	}
	
	@Override
	protected void doUpdate(Product item) throws YPCException {
		productService.update(item);
		getView().setEntity(productService.findById(item.getId()));
		saveImages(item);
	}
	
	private void saveImages(Product item) throws YPCException {
		imageFileService.update(ImageFileService.PRODUCT_TYPE,
				getView().getCurrentEntity().getId(), ((ProductView) getView()).getModifiedImages());
	}
	
	@Override
	protected Action[] getViewerActions() {
		return new Action[]{new DeleteProductAction(getView()), new EditItemAction<Product>(getView())};
	}

}
