package com.pinguela.yourpc.desktop.components;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public abstract class EntityCriteriaPanel<PK, T> extends CriteriaPanel<T> {

	private JFormattedTextField idField;

	public EntityCriteriaPanel() {
		GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};

		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.anchor = GridBagConstraints.EAST;
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 0;
		add(idLabel, gbc_idLabel);

		idField = ComponentFactory.createNullableNumberTextField(Long.class);
		GridBagConstraints gbc_idField = new GridBagConstraints();
		gbc_idField.gridwidth = getIdFieldGridWidth();
		gbc_idField.insets = new Insets(0, 0, 5, 5);
		gbc_idField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idField.gridx = 1;
		gbc_idField.gridy = 0;
		add(idField, gbc_idField);

		idField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				Document d = e.getDocument();

				if (e.getLength() > 1 || e.getOffset() > 0) {
					return;
				}

				setNonIdFieldsEnabled(true);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				setNonIdFieldsEnabled(false);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	@SuppressWarnings("unchecked")
	protected PK getId() {
		return (PK) idField.getValue();
	}

	private void setNonIdFieldsEnabled(boolean isEnabled) {
		setNonIdFieldsEnabled(this, isEnabled);
	}

	private void setNonIdFieldsEnabled(Container container, boolean isEnabled) {
		for (int i = 0; i < container.getComponentCount(); i++) {

			Component c = container.getComponent(i);

			if ((c instanceof JTextComponent && !c.equals(idField))
					|| c instanceof JComboBox
					|| c instanceof JSlider
					|| c instanceof JDateChooser) {
				c.setEnabled(isEnabled);
			} else if (c instanceof JPanel) {
				setNonIdFieldsEnabled((JPanel) c, isEnabled);
			}
		}
	}

	protected abstract int getIdFieldGridWidth();

}
