package com.pinguela.yourpc.desktop.components;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.pinguela.yourpc.desktop.actions.CloseTabAction;

/**
 * TODO: Handle index for tabs of the same type when they are not closed in order
 */
public class CloseableTabComponent 
extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5077714825691752359L;
	
	private JTabbedPane container;
	private JLabel titleLabel;
	
	private int indexOfTitleInstance;
	
	public CloseableTabComponent(JTabbedPane container, Component tab, String title) {
		
		this.container = container;
		this.indexOfTitleInstance = findIndexOfTitleInstance(title);
		
		titleLabel = new JLabel(appendTabCountToTitle(title));
		add(titleLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setMaximumSize(new Dimension(8, 32767));
		horizontalStrut.setMinimumSize(new Dimension(8, 0));
		horizontalStrut.setPreferredSize(new Dimension(8, 0));
		add(horizontalStrut);
		
		JButton btnNewButton = new JButton(new CloseTabAction(container, tab));
		add(btnNewButton);
		
		setOpaque(false);
	}
	
	public String getTitle() {
		return titleLabel.getText();
	}
	
	public int getIndexOfTitleInstance() {
		return indexOfTitleInstance;
	}
	
	private int findIndexOfTitleInstance(String title) {
		CloseableTabComponent lastComponent = findLastComponentWithTitle(title);
		return lastComponent == null ? 0 : lastComponent.getIndexOfTitleInstance() +1;
	}

	private CloseableTabComponent findLastComponentWithTitle(String title) {
		CloseableTabComponent lastComponent = null;

		for (int i = 0; i < container.getTabCount(); i++) {
		    Component tabComponent = container.getTabComponentAt(i);
		    if (tabComponent instanceof CloseableTabComponent) {
		        CloseableTabComponent closeableTabComponent = (CloseableTabComponent) tabComponent;
		        if (closeableTabComponent.getTitle().startsWith(title)
		        		&& (lastComponent == null || lastComponent.getIndexOfTitleInstance() < closeableTabComponent.getIndexOfTitleInstance())) {
		            lastComponent = closeableTabComponent;
		        }
		    }
		}
		return lastComponent;
	}
	
	private String appendTabCountToTitle(String title) {
		return indexOfTitleInstance > 0 ? title.concat(String.format(" (%d)", indexOfTitleInstance + 1)) : title;
	}

}
