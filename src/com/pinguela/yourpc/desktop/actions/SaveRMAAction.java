package com.pinguela.yourpc.desktop.actions;

import javax.swing.Action;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.RMAView;
import com.pinguela.yourpc.model.RMA;
import com.pinguela.yourpc.service.RMAService;
import com.pinguela.yourpc.service.impl.RMAServiceImpl;

@SuppressWarnings("serial")
public class SaveRMAAction 
extends SaveItemAction<RMA> {
	
	private RMAService rmaService = new RMAServiceImpl();

	public SaveRMAAction(RMAView view) {
		super(view, "Save", Icons.OK_ICON);
		this.rmaService = new RMAServiceImpl();
	}
	
	@Override
	protected void doCreate(RMA item) throws YPCException {
		rmaService.create(item);
		getView().setEntity(rmaService.findById(item.getId()));
	}
	
	@Override
	protected void doUpdate(RMA item) throws YPCException {
		rmaService.update(item);
		getView().setEntity(rmaService.findById(item.getId()));
	}
	
	@Override
	protected Action[] getViewerActions() {
		return new Action[]{new EditItemAction<RMA>(getView())};
	}

}
