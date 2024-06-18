package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.JTable;

import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.desktop.view.RMASearchView;

@SuppressWarnings("serial")
public class OpenRMASearchTabAction
extends OpenTabAction {
	
	public OpenRMASearchTabAction() {
		super("RMA search");
	}

	@Override
	protected Component initializeTab() {
		RMASearchView view = new RMASearchView();
		YPCAction openEditDialogAction = new OpenRMAResultDialogAction(view);
		
		JTable table = view.getTable();
		TableUtils.initializeActionPanes(table, openEditDialogAction);
		table.addMouseListener(openEditDialogAction);
		
		return view;
	}

}
