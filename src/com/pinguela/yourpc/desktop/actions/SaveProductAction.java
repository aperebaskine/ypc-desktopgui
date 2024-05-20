package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.ItemView;
import com.pinguela.yourpc.desktop.view.ProductItemView;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.service.ImageFileService;
import com.pinguela.yourpc.service.ProductService;
import com.pinguela.yourpc.service.impl.ImageFileServiceImpl;
import com.pinguela.yourpc.service.impl.ProductServiceImpl;

public class SaveProductAction 
extends ItemAction<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7296590252331794074L;
	
	private static Logger logger = LogManager.getLogger(SaveProductAction.class);
	
	private boolean closeOnSave = false;
	
	private ProductService productService;
	private ImageFileService imageFileService;
	
	public SaveProductAction(ProductItemView view) {
		this(view, false);
	}

	public SaveProductAction(ProductItemView view, boolean closeOnSave) {
		super(view, "Save", Icons.OK_ICON);
		this.productService = new ProductServiceImpl();
		this.imageFileService = new ImageFileServiceImpl();
		this.closeOnSave = closeOnSave;
	}

	@Override
	protected void doAction() {
		try {
			Product editedProduct = getView().getModifiedItem();
			productService.update(editedProduct);
			getView().setItem(editedProduct);
			imageFileService.update(ImageFileService.PRODUCT_TYPE,
					getView().getItem().getId(), ((ProductItemView) getView()).getModifiedImageEntries());
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			JOptionPane.showMessageDialog((Component) getView(), 
					"An error occured while updating the product.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if (closeOnSave) {
			SwingUtilities.getWindowAncestor((Component) getView()).dispose();
		} else {
			getView().showCard(ItemView.ACTION_CARD);
		}
	}

}
