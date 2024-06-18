package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ItemEvent;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.components.AttributeInputPane;
import com.pinguela.yourpc.desktop.components.InputPane;
import com.pinguela.yourpc.desktop.view.YPCView;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.model.AttributeValueHandlingModes;
import com.pinguela.yourpc.model.Category;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.service.impl.AttributeServiceImpl;

@SuppressWarnings("serial")
public abstract class GetAttributeInputAction<T extends YPCView>
extends GetInputAction<Attribute<?>> 
implements AttributeValueHandlingModes {

	private static Logger logger = LogManager.getLogger(GetAttributeInputAction.class);

	private AttributeService attributeService;
	private T view;
	
	private Integer forcedHandlingMode;

	public GetAttributeInputAction(String name, Icon icon, T view) {
		this(null, name, icon, view);
	}
	
	public GetAttributeInputAction(Integer forcedHandlingMode, String name, Icon icon, T view) {
		super(name, icon);
		this.attributeService = new AttributeServiceImpl();
		this.view = view;
		this.forcedHandlingMode = forcedHandlingMode;
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
		return new AttributeInputPane(attributes, forcedHandlingMode, AttributeService.NO_UNASSIGNED_VALUES);
	}
	
	public T getView() {
		return view;
	}
	
	protected abstract Short getCategoryId();

}
