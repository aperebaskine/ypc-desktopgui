package com.pinguela.yourpc.desktop.constants;

import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.yourpc.model.CustomerOrder;
import com.pinguela.yourpc.model.Department;
import com.pinguela.yourpc.model.ItemState;
import com.pinguela.yourpc.model.ItemType;
import com.pinguela.yourpc.model.RMA;
import com.pinguela.yourpc.model.Ticket;
import com.pinguela.yourpc.service.DepartmentService;
import com.pinguela.yourpc.service.OrderStateService;
import com.pinguela.yourpc.service.RMAStateService;
import com.pinguela.yourpc.service.TicketStateService;
import com.pinguela.yourpc.service.TicketTypeService;
import com.pinguela.yourpc.service.impl.DepartmentServiceImpl;
import com.pinguela.yourpc.service.impl.OrderStateServiceImpl;
import com.pinguela.yourpc.service.impl.RMAStateServiceImpl;
import com.pinguela.yourpc.service.impl.TicketStateServiceImpl;
import com.pinguela.yourpc.service.impl.TicketTypeServiceImpl;

public class DBConstants {
	
	private static Logger logger = LogManager.getLogger(DBConstants.class);

    // Define the final constants for the maps
    public static final Map<String, ItemState<Ticket>> TICKET_STATES;
    public static final Map<String, ItemType<Ticket>> TICKET_TYPES;
    public static final Map<String, ItemState<RMA>> RMA_STATES;
    public static final Map<String, ItemState<CustomerOrder>> ORDER_STATES;
    public static final Map<String, Department> DEPARTMENTS;

    static {
        Map<String, ItemState<Ticket>> ticketStates = null;
        Map<String, ItemType<Ticket>> ticketTypes = null;
        Map<String, ItemState<RMA>> rmaStates = null;
        Map<String, ItemState<CustomerOrder>> orderStates = null;
        Map<String, Department> departments = null;

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
        } catch (Throwable t) {
            logger.fatal(t.getMessage(), t);
        }

        TICKET_STATES = Collections.unmodifiableMap(ticketStates);
        TICKET_TYPES = Collections.unmodifiableMap(ticketTypes);
        RMA_STATES = Collections.unmodifiableMap(rmaStates);
        ORDER_STATES = Collections.unmodifiableMap(orderStates);
        DEPARTMENTS = Collections.unmodifiableMap(departments);
    }
    
}
