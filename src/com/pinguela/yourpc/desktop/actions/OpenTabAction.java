package com.pinguela.yourpc.desktop.actions;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.yourpc.desktop.YPCWindow;
import com.pinguela.yourpc.desktop.view.YPCView;

public class OpenTabAction<T extends YPCView> extends YPCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1477001024461349414L;
	
	private static Logger logger = LogManager.getLogger(OpenTabAction.class);
	
	private Class<T> target;
	private String title;
	
	public OpenTabAction(Class<T> target, String title) {
		this.target = target;
		this.title = title;
	}

	@Override
	protected void doAction() {
		try {
			YPCWindow.getInstance().addCloseableTab(title, target.getDeclaredConstructor().newInstance());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JOptionPane.showMessageDialog(null, "An error occured while opening the tab.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
