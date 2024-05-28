package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.ServiceException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.ItemView;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.desktop.view.ProductView;
import com.pinguela.yourpc.model.ImageEntry;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.service.ImageFileService;
import com.pinguela.yourpc.service.impl.ImageFileServiceImpl;

public class EditProductDialogAction 
extends AbstractDialogAction<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6020214024891107072L;

	private static Logger logger = LogManager.getLogger(EditProductDialogAction.class);

	private ProductSearchView searchView;
	private ProductView dialogView;

	private Product p;

	private ImageFileService imageFileService;

	public EditProductDialogAction(ProductSearchView view) {
		super(Icons.EDIT_ICON);
		this.imageFileService = new ImageFileServiceImpl();
		this.searchView = view;
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		JTable table = searchView.getTable();
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();

		dialogView = new ProductView();
		dialogView.addPropertyChangeListener(ItemView.ITEM_PROPERTY, this);
		p = (Product) table.getValueAt(row, column);
		dialogView.setItem(p);

		boolean isEditing = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!isEditing) {
			dialogView.addAction(new ItemEditAction<Product>(dialogView, ItemView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<Product>(dialogView), ItemView.EDITOR_CARD);
		dialogView.addAction(new SaveProductAction(dialogView), ItemView.EDITOR_CARD);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);
		YPCDialog dialog = new YPCDialog(frame, dialogView);
		dialog.setTitle("Product editor");
		return dialog;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		ProductView view = (ProductView) evt.getSource();
		Product old = (Product) evt.getOldValue();
		Product product = (Product) evt.getNewValue();

		if (old != null && old.getId() == product.getId()) { // Product was updated
			searchView.doSearch();
			return;
		}

		try {
			view.clearImages();
			List<ImageEntry> imageFiles = imageFileService.getFiles("product", product.getId());
			view.addImages(imageFiles);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			JOptionPane.showMessageDialog(view, 
					"An error occured while fetching images.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}

	@Override
	protected void onClose() {}

}
