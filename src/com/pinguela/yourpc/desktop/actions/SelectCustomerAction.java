package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import javax.swing.table.TableCellEditor;

import com.pinguela.yourpc.desktop.components.CustomerSelector;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.desktop.view.CustomerSearchView;
import com.pinguela.yourpc.model.Customer;

public class SelectCustomerAction 
extends OpenDialogAction<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7530212540747654702L;
	
	private CustomerSelector selector;
	private CustomerSearchView view;
	
	public SelectCustomerAction(CustomerSelector selector) {
		super("Select...");
		this.selector = selector;
	}
	
	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		view = new CustomerSearchView();
		TableUtils.initializeActionPanes(view.getTable(), new CloseDialogAction(Icons.OK_ICON, view));
		return new YPCDialog(null, view);
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return true;
	}

	@Override
	protected void onClose() {
		TableCellEditor editor = view.getTable().getCellEditor();
		
		if (editor == null) {
			return;
		}
		
		Customer c = (Customer) view.getTable().getCellEditor().getCellEditorValue();
		
		if (c == null) {
			return;
		}
		
		selector.setItem(c);
	}

}
