package com.pinguela.yourpc.desktop.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Create services to access the data directly from the database
 */
public class DBConstants {
	
	// Define the final constants for the maps
	public static final Map<String, String> TICKET_STATES;
	public static final Map<String, String> TICKET_TYPES;
	public static final Map<String, String> RMA_STATES;
	public static final Map<String, String> ORDER_STATES;
	public static final Map<String, String> DOCUMENT_TYPES;
	public static final Map<String, String> DEPARTMENTS;

	static {
		// Initialize and populate the TICKET_STATES map
		Map<String, String> ticketStates = new HashMap<>();
		ticketStates.put("OPN", "Open");
		ticketStates.put("PGS", "In Progress");
		ticketStates.put("RES", "Resolved");
		ticketStates.put("CLO", "Closed");
		TICKET_STATES = Collections.unmodifiableMap(ticketStates);

		// Initialize and populate the TICKET_TYPES map
		Map<String, String> ticketTypes = new HashMap<>();
		ticketTypes.put("SUP", "Technical Support");
		ticketTypes.put("BIL", "Billing Inquiry");
		ticketTypes.put("PRO", "Product Inquiry");
		ticketTypes.put("RMA", "Warranty and Returns");
		TICKET_TYPES = Collections.unmodifiableMap(ticketTypes);

		// Initialize and populate the RMA_STATES map
		Map<String, String> rmaStates = new HashMap<>();
		rmaStates.put("REC", "Received");
		rmaStates.put("PRS", "Processing");
		rmaStates.put("APP", "Approved");
		rmaStates.put("REJ", "Rejected");
		RMA_STATES = Collections.unmodifiableMap(rmaStates);

		// Initialize and populate the ORDER_STATES map
		Map<String, String> orderStates = new HashMap<>();
		orderStates.put("PND", "Pending");
		orderStates.put("PRS", "Processing");
		orderStates.put("SPD", "Shipped");
		orderStates.put("DEL", "Delivered");
		orderStates.put("CAN", "Cancelled");
		ORDER_STATES = Collections.unmodifiableMap(orderStates);
		
		// Initialize and populate the DOCUMENT_TYPES map
		Map<String, String> documentTypes = new HashMap<>();
		documentTypes.put("NIF", "National Identification Number");
		documentTypes.put("NIE", "Foreign Identification Number");
		documentTypes.put("PPT", "Passport");
		documentTypes.put("FOR", "Foreign ID");
		DOCUMENT_TYPES = Collections.unmodifiableMap(documentTypes);

		// Initialize and populate the DEPARTMENTS map
		Map<String, String> departments = new HashMap<>();
		departments.put("SAL", "Sales");
		departments.put("MKT", "Marketing");
		departments.put("HRS", "Human Resources");
		departments.put("EXC", "Executive");
		departments.put("FIN", "Finance");
		departments.put("OPS", "Operations");
		departments.put("SUP", "Support");
		DEPARTMENTS = Collections.unmodifiableMap(departments);
	}
}
