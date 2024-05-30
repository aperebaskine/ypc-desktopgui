package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.JTable;

import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.desktop.view.RMASearchView;

public class OpenRMASearchTabAction extends OpenTabAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7760123193599046522L;
	
	public OpenRMASearchTabAction() {
		super("RMA search");
	}

	@Override
	protected Component initializeTab() {
		RMASearchView view = new RMASearchView();
		YPCAction openEditDialogAction = new EditRMADialogAction(view);
		
		JTable table = view.getTable();
		TableUtils.initializeActionPanes(table, openEditDialogAction);
		table.addMouseListener(openEditDialogAction);
		
		return view;
	}

}
