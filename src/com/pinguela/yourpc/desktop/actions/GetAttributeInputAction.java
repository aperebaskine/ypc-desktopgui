package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ItemEvent;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.components.AttributeInputPane;
import com.pinguela.yourpc.desktop.components.InputPane;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.YPCView;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.model.Category;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.service.impl.AttributeServiceImpl;

public abstract class GetAttributeInputAction<T extends YPCView>
extends GetInputAction<Attribute<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3228435100642492498L;

	private static Logger logger = LogManager.getLogger(GetAttributeInputAction.class);

	private AttributeService attributeService;
	private T view;

	public GetAttributeInputAction(T view) {
		super("Add attribute...", Icons.ADD_ICON);
		this.attributeService = new AttributeServiceImpl();
		this.view = view;
		setEnabled(false);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Category c = (Category) e.getItem();
		setEnabled(c.getId() != null);
	}

	@Override
	protected InputPane<Attribute<?>> initializeInputPane() {
		Map<String, Attribute<?>> attributes = new TreeMap<String, Attribute<?>>();
		try {
			attributes.putAll(attributeService.findByCategory(getCategoryId(), AttributeService.NO_UNASSIGNED_VALUES));
		} catch (YPCException e) {
			logger.error(String.format("An error occured while fetching attributes: %s", e.getMessage()), e);
			JOptionPane.showMessageDialog(view, "An error occured. Contact system administrador", "Error", JOptionPane.ERROR_MESSAGE);
		} 
		return new AttributeInputPane(attributes, AttributeService.NO_UNASSIGNED_VALUES);
	}
	
	public T getView() {
		return view;
	}
	
	protected abstract Short getCategoryId();

}
