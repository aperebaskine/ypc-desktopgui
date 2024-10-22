package com.pinguela.yourpc.desktop.constants;

import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.yourpc.model.Department;
import com.pinguela.yourpc.model.IDType;
import com.pinguela.yourpc.model.OrderState;
import com.pinguela.yourpc.model.RMAState;
import com.pinguela.yourpc.model.TicketState;
import com.pinguela.yourpc.model.TicketType;
import com.pinguela.yourpc.service.DepartmentService;
import com.pinguela.yourpc.service.DocumentTypeService;
import com.pinguela.yourpc.service.OrderStateService;
import com.pinguela.yourpc.service.RMAStateService;
import com.pinguela.yourpc.service.TicketStateService;
import com.pinguela.yourpc.service.TicketTypeService;
import com.pinguela.yourpc.service.impl.DepartmentServiceImpl;
import com.pinguela.yourpc.service.impl.DocumentTypeServiceImpl;
import com.pinguela.yourpc.service.impl.OrderStateServiceImpl;
import com.pinguela.yourpc.service.impl.RMAStateServiceImpl;
import com.pinguela.yourpc.service.impl.TicketStateServiceImpl;
import com.pinguela.yourpc.service.impl.TicketTypeServiceImpl;

public class DBConstants {
	
	private static Logger logger = LogManager.getLogger(DBConstants.class);

    // Define the final constants for the maps
    public static final Map<String, TicketState> TICKET_STATES;
    public static final Map<String, TicketType> TICKET_TYPES;
    public static final Map<String, RMAState> RMA_STATES;
    public static final Map<String, OrderState> ORDER_STATES;
    public static final Map<String, Department> DEPARTMENTS;
    public static final Map<String, IDType> DOCUMENT_TYPES;

    static {
        Map<String, TicketState> ticketStates = null;
        Map<String, TicketType> ticketTypes = null;
        Map<String, RMAState> rmaStates = null;
        Map<String, OrderState> orderStates = null;
        Map<String, Department> departments = null;
        Map<String, IDType> documentTypes = null;

        try {
            TicketStateService ticketStateService = new TicketStateServiceImpl();
            ticketStates = ticketStateService.findAll();
            
            TicketTypeService ticketTypeService = new TicketTypeServiceImpl();
            ticketTypes = ticketTypeService.findAll();

            RMAStateService rmaStateService = new RMAStateServiceImpl();
            rmaStates = rmaStateService.findAll();

            OrderStateService orderStateService = new OrderStateServiceImpl();
            orderStates = orderStateService.findAll();
            
            DepartmentService departmentService = new DepartmentServiceImpl();
            departments = departmentService.findAll();
            
            DocumentTypeService documentTypeService = new DocumentTypeServiceImpl();
            documentTypes = documentTypeService.findAll();
        } catch (Throwable t) {
            logger.fatal(t.getMessage(), t);
        }

        TICKET_STATES = Collections.unmodifiableMap(ticketStates);
        TICKET_TYPES = Collections.unmodifiableMap(ticketTypes);
        RMA_STATES = Collections.unmodifiableMap(rmaStates);
        ORDER_STATES = Collections.unmodifiableMap(orderStates);
        DEPARTMENTS = Collections.unmodifiableMap(departments);
        DOCUMENT_TYPES = Collections.unmodifiableMap(documentTypes);
    }
    
}
