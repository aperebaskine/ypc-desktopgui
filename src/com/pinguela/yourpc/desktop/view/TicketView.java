package com.pinguela.yourpc.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.model.ItemState;
import com.pinguela.yourpc.model.ItemType;
import com.pinguela.yourpc.model.Ticket;

public class TicketView 
extends AbstractItemView<Ticket> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5136525003881354059L;
	
	private JLabel idValueLabel;
	private JTextField customerIdTextField;
	private JTextField titleTextField;
	private JComboBox<ItemType<Ticket>> typeComboBox;
	private JComboBox<ItemState<Ticket>> stateComboBox;
	private JPanel messageListPanel;
	
	public TicketView() {
		initialize();
		postInitialize();
	}
	
	private void initialize() {
		GridBagLayout gridBagLayout = (GridBagLayout) getViewPanel().getLayout();
		gridBagLayout.columnWidths = new int[]{0, 200, 48, 0, 240};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		
		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.anchor = GridBagConstraints.EAST;
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 0;
		getViewPanel().add(idLabel, gbc_idLabel);
		
		idValueLabel = new JLabel("0");
		GridBagConstraints gbc_idValueLabel = new GridBagConstraints();
		gbc_idValueLabel.anchor = GridBagConstraints.WEST;
		gbc_idValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLabel.gridx = 1;
		gbc_idValueLabel.gridy = 0;
		getViewPanel().add(idValueLabel, gbc_idValueLabel);
		
		JLabel messageListLabel = new JLabel("Messages:");
		GridBagConstraints gbc_messageListLabel = new GridBagConstraints();
		gbc_messageListLabel.insets = new Insets(0, 0, 5, 5);
		gbc_messageListLabel.gridx = 3;
		gbc_messageListLabel.gridy = 0;
		getViewPanel().add(messageListLabel, gbc_messageListLabel);
		
		JScrollPane messageListScrollPane = new JScrollPane();
		GridBagConstraints gbc_messageListScrollPane = new GridBagConstraints();
		gbc_messageListScrollPane.gridheight = 8;
		gbc_messageListScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_messageListScrollPane.fill = GridBagConstraints.BOTH;
		gbc_messageListScrollPane.gridx = 4;
		gbc_messageListScrollPane.gridy = 0;
		getViewPanel().add(messageListScrollPane, gbc_messageListScrollPane);
		
		messageListPanel = new JPanel();
		messageListScrollPane.setViewportView(messageListPanel);
		messageListPanel.setLayout(new BoxLayout(messageListPanel, BoxLayout.Y_AXIS));
		
		JLabel creationDateLabel = new JLabel("Creation Date:");
		GridBagConstraints gbc_creationDateLabel = new GridBagConstraints();
		gbc_creationDateLabel.anchor = GridBagConstraints.EAST;
		gbc_creationDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_creationDateLabel.gridx = 0;
		gbc_creationDateLabel.gridy = 1;
		getViewPanel().add(creationDateLabel, gbc_creationDateLabel);
		
		JLabel creationDateValueLabel = new JLabel("01/01/1970");
		GridBagConstraints gbc_creationDateValueLabel = new GridBagConstraints();
		gbc_creationDateValueLabel.anchor = GridBagConstraints.WEST;
		gbc_creationDateValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_creationDateValueLabel.gridx = 1;
		gbc_creationDateValueLabel.gridy = 1;
		getViewPanel().add(creationDateValueLabel, gbc_creationDateValueLabel);
		
		JLabel customerIdLabel = new JLabel("Customer ID:");
		GridBagConstraints gbc_customerIdLabel = new GridBagConstraints();
		gbc_customerIdLabel.anchor = GridBagConstraints.EAST;
		gbc_customerIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdLabel.gridx = 0;
		gbc_customerIdLabel.gridy = 2;
		getViewPanel().add(customerIdLabel, gbc_customerIdLabel);
		
		customerIdTextField = new JTextField();
		GridBagConstraints gbc_customerIdTextField = new GridBagConstraints();
		gbc_customerIdTextField.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerIdTextField.gridx = 1;
		gbc_customerIdTextField.gridy = 2;
		getViewPanel().add(customerIdTextField, gbc_customerIdTextField);
		customerIdTextField.setColumns(10);
		
		JLabel typeLabel = new JLabel("Type:");
		GridBagConstraints gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.anchor = GridBagConstraints.EAST;
		gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_typeLabel.gridx = 0;
		gbc_typeLabel.gridy = 3;
		getViewPanel().add(typeLabel, gbc_typeLabel);
		
		JLabel stateLabel = new JLabel("State:");
		GridBagConstraints gbc_stateLabel = new GridBagConstraints();
		gbc_stateLabel.anchor = GridBagConstraints.EAST;
		gbc_stateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_stateLabel.gridx = 0;
		gbc_stateLabel.gridy = 4;
		getViewPanel().add(stateLabel, gbc_stateLabel);
		
		JLabel titleLabel = new JLabel("Title:");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.EAST;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 5;
		getViewPanel().add(titleLabel, gbc_titleLabel);
		
		titleTextField = new JTextField();
		GridBagConstraints gbc_titleTextField = new GridBagConstraints();
		gbc_titleTextField.insets = new Insets(0, 0, 5, 5);
		gbc_titleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleTextField.gridx = 1;
		gbc_titleTextField.gridy = 5;
		getViewPanel().add(titleTextField, gbc_titleTextField);
		titleTextField.setColumns(10);
		
		JLabel descriptionLabel = new JLabel("Description:");
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 0;
		gbc_descriptionLabel.gridy = 6;
		getViewPanel().add(descriptionLabel, gbc_descriptionLabel);
		
		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setWrapStyleWord(true);
		GridBagConstraints gbc_descriptionTextArea = new GridBagConstraints();
		gbc_descriptionTextArea.insets = new Insets(0, 0, 0, 5);
		gbc_descriptionTextArea.gridheight = 2;
		gbc_descriptionTextArea.fill = GridBagConstraints.BOTH;
		gbc_descriptionTextArea.gridx = 1;
		gbc_descriptionTextArea.gridy = 6;
		getViewPanel().add(descriptionTextArea, gbc_descriptionTextArea);
	}
	
	private void postInitialize() {
		typeComboBox = ComponentFactory.createComboBox(DBConstants.TICKET_TYPES.values(), ItemType.class);
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridx = 1;
		gbc_typeComboBox.gridy = 3;
		getViewPanel().add(typeComboBox, gbc_typeComboBox);
		
		stateComboBox = ComponentFactory.createComboBox(DBConstants.TICKET_STATES.values(), ItemState.class);
		GridBagConstraints gbc_stateComboBox = new GridBagConstraints();
		gbc_stateComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_stateComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateComboBox.gridx = 1;
		gbc_stateComboBox.gridy = 4;
		getViewPanel().add(stateComboBox, gbc_stateComboBox);
	}

	@Override
	public Ticket getNewItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onItemSet() {
		// TODO Auto-generated method stub
		
	}

}
