package com.pinguela.yourpc.desktop.components;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.pinguela.yourpc.desktop.renderer.AttributeListCellRenderer;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.model.AttributeValue;

public class StringAttributeInputPane 
extends AttributeInputPane<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3479487153780500916L;
	
	private JScrollPane scrollPane;

	/**
	 * Unused constructor required for rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings("unused")
	private StringAttributeInputPane() {
		this(Attribute.getInstance(String.class));
	}

	public StringAttributeInputPane(Attribute<String> attribute) {
		super(attribute);
	}

	@Override
	protected JPanel initializeContentPane() {

		JPanel contentPane = new JPanel();

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(200, 100));
		contentPane.add(scrollPane);

		return contentPane;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void setInitialValues() {
		JList<AttributeValue<String>> list = 
				new JList<AttributeValue<String>>(
						(AttributeValue<String>[]) getAttribute().getValues().toArray(
								new AttributeValue[getAttribute().getValues().size()])
						);
		list.setCellRenderer(new AttributeListCellRenderer());
		scrollPane.setViewportView(list);

	}

}
