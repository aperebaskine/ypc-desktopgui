package com.pinguela.yourpc.desktop.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.components.CustomerSelector;
import com.pinguela.yourpc.desktop.components.TicketMessagePanel;
import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.ItemState;
import com.pinguela.yourpc.model.ItemType;
import com.pinguela.yourpc.model.Ticket;
import com.pinguela.yourpc.model.TicketMessage;
import com.pinguela.yourpc.service.CustomerService;
import com.pinguela.yourpc.service.impl.CustomerServiceImpl;

public class TicketView 
extends AbstractEntityView<Ticket> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5136525003881354059L;
	
	private static Logger logger = LogManager.getLogger(TicketView.class);
	
	private static final String MESSAGE_LIST_SIZE_PROPERTY = "messageListSize";
	
	private CustomerService customerService;

	private JLabel idValueLabel;
	private JLabel creationDateValueLabel;
	private CustomerSelector customerSelectionPanel;
	private JTextField titleTextField;
	private JComboBox<ItemType<Ticket>> typeComboBox;
	private JComboBox<ItemState<Ticket>> stateComboBox;
	private JTextArea descriptionTextArea;
	private JPanel messageListPanel;
	private List<TicketMessage> messages;
	
	private PropertyChangeListener messageListSizeListener = (evt) -> {
		GridLayout layout = (GridLayout) messageListPanel.getLayout();
		layout.setRows(layout.getRows() +1);
		messageListPanel.add(new TicketMessagePanel(messages.get((int) evt.getNewValue() -1)));
		messageListPanel.revalidate();
		messageListPanel.repaint();
	};

	public TicketView() {
		initialize();
		postInitialize();
		this.customerService = new CustomerServiceImpl();
	}

	private void initialize() {
		
		JPanel viewPanel = getViewPanel();
		
		GridBagLayout gridBagLayout = (GridBagLayout) viewPanel.getLayout();
		gridBagLayout.columnWidths = new int[]{0, 200, 48, 0, 240};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};

		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.anchor = GridBagConstraints.EAST;
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 0;
		viewPanel.add(idLabel, gbc_idLabel);

		idValueLabel = new JLabel("0");
		GridBagConstraints gbc_idValueLabel = new GridBagConstraints();
		gbc_idValueLabel.anchor = GridBagConstraints.WEST;
		gbc_idValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLabel.gridx = 1;
		gbc_idValueLabel.gridy = 0;
		viewPanel.add(idValueLabel, gbc_idValueLabel);

		JLabel messageListLabel = new JLabel("Messages:");
		GridBagConstraints gbc_messageListLabel = new GridBagConstraints();
		gbc_messageListLabel.insets = new Insets(0, 0, 5, 5);
		gbc_messageListLabel.gridx = 3;
		gbc_messageListLabel.gridy = 0;
		viewPanel.add(messageListLabel, gbc_messageListLabel);

		JScrollPane messageListScrollPane = new JScrollPane();
		messageListScrollPane.setPreferredSize(new Dimension(480, 360));
		messageListScrollPane.setMinimumSize(new Dimension(480, 360));
		messageListScrollPane.setMaximumSize(new Dimension(480, 32767));
		GridBagConstraints gbc_messageListScrollPane = new GridBagConstraints();
		gbc_messageListScrollPane.gridheight = 8;
		gbc_messageListScrollPane.fill = GridBagConstraints.BOTH;
		gbc_messageListScrollPane.gridx = 4;
		gbc_messageListScrollPane.gridy = 0;
		viewPanel.add(messageListScrollPane, gbc_messageListScrollPane);

		messageListPanel = new JPanel();
		messageListPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		messageListPanel.setMinimumSize(new Dimension(480, 10));
		messageListPanel.setMaximumSize(new Dimension(480, 32767));
		messageListScrollPane.setViewportView(messageListPanel);
		messageListPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel creationDateLabel = new JLabel("Creation Date:");
		GridBagConstraints gbc_creationDateLabel = new GridBagConstraints();
		gbc_creationDateLabel.anchor = GridBagConstraints.EAST;
		gbc_creationDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_creationDateLabel.gridx = 0;
		gbc_creationDateLabel.gridy = 1;
		viewPanel.add(creationDateLabel, gbc_creationDateLabel);

		creationDateValueLabel = new JLabel("01/01/1970");
		GridBagConstraints gbc_creationDateValueLabel = new GridBagConstraints();
		gbc_creationDateValueLabel.anchor = GridBagConstraints.WEST;
		gbc_creationDateValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_creationDateValueLabel.gridx = 1;
		gbc_creationDateValueLabel.gridy = 1;
		viewPanel.add(creationDateValueLabel, gbc_creationDateValueLabel);

		JLabel customerLabel = new JLabel("Customer:");
		GridBagConstraints gbc_customerLabel = new GridBagConstraints();
		gbc_customerLabel.anchor = GridBagConstraints.EAST;
		gbc_customerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerLabel.gridx = 0;
		gbc_customerLabel.gridy = 2;
		viewPanel.add(customerLabel, gbc_customerLabel);
		
		customerSelectionPanel = new CustomerSelector();
		GridBagConstraints gbc_customerSelectionPanel = new GridBagConstraints();
		gbc_customerSelectionPanel.insets = new Insets(0, 0, 5, 5);
		gbc_customerSelectionPanel.fill = GridBagConstraints.BOTH;
		gbc_customerSelectionPanel.gridx = 1;
		gbc_customerSelectionPanel.gridy = 2;
		viewPanel.add(customerSelectionPanel, gbc_customerSelectionPanel);

		JLabel typeLabel = new JLabel("Type:");
		GridBagConstraints gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.anchor = GridBagConstraints.EAST;
		gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_typeLabel.gridx = 0;
		gbc_typeLabel.gridy = 3;
		viewPanel.add(typeLabel, gbc_typeLabel);

		JLabel stateLabel = new JLabel("State:");
		GridBagConstraints gbc_stateLabel = new GridBagConstraints();
		gbc_stateLabel.anchor = GridBagConstraints.EAST;
		gbc_stateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_stateLabel.gridx = 0;
		gbc_stateLabel.gridy = 4;
		viewPanel.add(stateLabel, gbc_stateLabel);

		JLabel titleLabel = new JLabel("Title:");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.EAST;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 5;
		viewPanel.add(titleLabel, gbc_titleLabel);

		titleTextField = new JTextField();
		GridBagConstraints gbc_titleTextField = new GridBagConstraints();
		gbc_titleTextField.insets = new Insets(0, 0, 5, 5);
		gbc_titleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleTextField.gridx = 1;
		gbc_titleTextField.gridy = 5;
		viewPanel.add(titleTextField, gbc_titleTextField);
		titleTextField.setColumns(10);

		JLabel descriptionLabel = new JLabel("Description:");
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 0;
		gbc_descriptionLabel.gridy = 6;
		viewPanel.add(descriptionLabel, gbc_descriptionLabel);

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setWrapStyleWord(true);
		GridBagConstraints gbc_descriptionTextArea = new GridBagConstraints();
		gbc_descriptionTextArea.insets = new Insets(0, 0, 0, 5);
		gbc_descriptionTextArea.gridheight = 2;
		gbc_descriptionTextArea.fill = GridBagConstraints.BOTH;
		gbc_descriptionTextArea.gridx = 1;
		gbc_descriptionTextArea.gridy = 6;
		viewPanel.add(descriptionTextArea, gbc_descriptionTextArea);
	}

	private void postInitialize() {
		JPanel viewPanel = getViewPanel();
		
		typeComboBox = ComponentFactory.createComboBox(DBConstants.TICKET_TYPES.values(), ItemType.class);
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridx = 1;
		gbc_typeComboBox.gridy = 3;
		viewPanel.add(typeComboBox, gbc_typeComboBox);

		stateComboBox = ComponentFactory.createComboBox(DBConstants.TICKET_STATES.values(), ItemState.class);
		GridBagConstraints gbc_stateComboBox = new GridBagConstraints();
		gbc_stateComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_stateComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateComboBox.gridx = 1;
		gbc_stateComboBox.gridy = 4;
		viewPanel.add(stateComboBox, gbc_stateComboBox);

		messages = new ArrayList<TicketMessage>();
		addPropertyChangeListener(MESSAGE_LIST_SIZE_PROPERTY, messageListSizeListener);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Ticket getEntityFromFields() {
		Ticket ticket = new Ticket();
		Ticket updating = getCurrentEntity();
		
		if (updating != null) {
			ticket.setId(updating.getId());
			ticket.setCustomerId(updating.getCustomerId());
			ticket.setEmployeeId(updating.getEmployeeId());
			ticket.setCreationDate(updating.getCreationDate());
		}

		if (!idValueLabel.getText().isEmpty()) {
			ticket.setId(Long.parseLong(idValueLabel.getText()));
		}
		
		ticket.setCustomerId(customerSelectionPanel.getCustomerId());

		ticket.setTitle(titleTextField.getText());

		if (!descriptionTextArea.getText().isEmpty()) {
			ticket.setDescription(descriptionTextArea.getText());
		}

		if (typeComboBox.getSelectedItem() != null) {
			ticket.setType(((ItemType<Ticket>) typeComboBox.getSelectedItem()).getId());
		}

		if (stateComboBox.getSelectedItem() != null) {
			ticket.setState(((ItemState<Ticket>) stateComboBox.getSelectedItem()).getId());
		}

		ticket.getMessageList().addAll(messages);

		return ticket;
	}

	@Override
	public void resetFields() {
		idValueLabel.setText("");
		creationDateValueLabel.setText("");
		if (getCurrentEntity() == null) {
			customerSelectionPanel.setItem(null);
		}
		titleTextField.setText("");
		descriptionTextArea.setText("");
		typeComboBox.setSelectedIndex(0);
		stateComboBox.setSelectedIndex(0);
		messages.clear();
		messageListPanel.removeAll();
		((GridLayout) messageListPanel.getLayout()).setRows(1);
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		titleTextField.setEditable(isEditable);
		descriptionTextArea.setEditable(isEditable);
		typeComboBox.setEnabled(isEditable);
		stateComboBox.setEnabled(isEditable);
	}

	@Override
	protected void loadItemData() {
		resetFields();
		
		Ticket ticket = getCurrentEntity();
		
		idValueLabel.setText(ticket.getId() != null ? ticket.getId().toString() : "");
		creationDateValueLabel.setText(ticket.getCreationDate() != null ? SwingUtils.formatDateTime(ticket.getCreationDate()): "");
		try {
			customerSelectionPanel.setItem(customerService.findById(ticket.getCustomerId()));
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			SwingUtils.showDatabaseAccessErrorDialog(this);
		} 
		titleTextField.setText(ticket.getTitle());
		descriptionTextArea.setText(ticket.getDescription());
		typeComboBox.setSelectedItem(DBConstants.TICKET_TYPES.get(ticket.getType()));
		stateComboBox.setSelectedItem(DBConstants.TICKET_STATES.get(ticket.getState()));
		
		messages.clear();
		for (TicketMessage message : ticket.getMessageList()) {
			addMessage(message);
		}
	}

	public void addMessage(TicketMessage message) {
		int size = messages.size();
		messages.add(message);
		firePropertyChange(MESSAGE_LIST_SIZE_PROPERTY, size, size+1);
	}

}
