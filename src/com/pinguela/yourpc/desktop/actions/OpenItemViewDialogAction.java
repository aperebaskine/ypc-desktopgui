package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.yourpc.desktop.components.EntitySelector;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.YPCView;

@SuppressWarnings("serial")
public class OpenItemViewDialogAction<T>
extends OpenDialogAction<T> {
	
	private static Logger logger = LogManager.getLogger(OpenItemViewDialogAction.class);
	
	private Class<? extends EntityView<T>> target;
	private EntitySelector<T> selector;
	
	public <V extends EntityView<T>, S extends EntitySelector<T>> OpenItemViewDialogAction(Class<V> target, S selector) {
		super("View");
		this.target = target;
		this.selector = selector;
	}
	
	protected EntityView<T> initializeView() throws Exception {
		return (EntityView<T>) target.getDeclaredConstructor().newInstance();
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		EntityView<T> dialogView;
		
		try {
			dialogView = initializeView();
			dialogView.setEntity(selector.getItem());
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
			throw new IllegalStateException(e1.getMessage(), e1);
		} 
		
		YPCDialog dialog = new YPCDialog(null, (YPCView) dialogView);
		dialog.setTitle("Item view");
		return dialog;
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}

	@Override
	protected void onClose() {}

}
