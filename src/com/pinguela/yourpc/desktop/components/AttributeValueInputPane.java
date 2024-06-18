package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.pinguela.yourpc.model.Attribute;

public class AttributeValueInputPane 
extends InputPane<Attribute<?>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5511895556394282873L;
	
	private JPanel contentPane;
	private AttributeEditor<?> editor;
	
	private Attribute<?> attribute;
	
	public AttributeValueInputPane(Attribute<?> attribute) {
		this(attribute, null, true);
	}
	
	public AttributeValueInputPane(Attribute<?> attribute, boolean showUnassignedValues) {
		this(attribute, null, showUnassignedValues);
	}
	
	public AttributeValueInputPane(Attribute<?> attribute, Integer handlingMode, boolean showUnassignedValues) {
		super(String.format("Select values for %s:", attribute.getName()));
		this.attribute = attribute;
		initializeEditor(attribute, handlingMode, showUnassignedValues);
	}

	@Override
	protected JPanel initializeContentPane() {
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		return contentPane;
	}
	
	private void initializeEditor(Attribute<?> attribute, Integer handlingMode, boolean showUnassignedValues) {
		editor = AttributeEditor.getInstance(attribute, handlingMode, showUnassignedValues);
		contentPane.add(editor, BorderLayout.CENTER);
	}

	@Override
	public Attribute<?> getInput() {
		Attribute<?> attribute = this.attribute.clone();
		attribute.removeAllValues();
		
		for (Object value : editor.getEditorValues()) {
			attribute.addValue(null, value);
		}
		return attribute;
	}

}
