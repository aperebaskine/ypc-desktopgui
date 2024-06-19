package com.pinguela.yourpc.desktop.components;

import com.pinguela.yourpc.desktop.actions.OpenCustomerAddressViewDialogAction;
import com.pinguela.yourpc.desktop.actions.YPCAction;
import com.pinguela.yourpc.model.AbstractPerson;
import com.pinguela.yourpc.model.Address;

@SuppressWarnings("serial")
public class CustomerAddressSelector 
extends EntitySelector<Address> {
	
	private Integer ownerId;
	
	public CustomerAddressSelector(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	public CustomerAddressSelector(AbstractPerson person) {
		this.ownerId = person.getId();
	}
	
	public CustomerAddressSelector(EntitySelector<? extends AbstractPerson> ownerSelector) {
		ownerSelector.addPropertyChangeListener(ENTITY_PROPERTY, (evt) -> {
			AbstractPerson customer = (AbstractPerson) evt.getNewValue();
			
			if (customer != null) {
				setCustomerId(customer.getId());
			}
		});
	}
	
	public Integer getCustomerId() {
		return ownerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.ownerId = customerId;
	}

	@Override
	protected YPCAction initializeViewAction() {
		return new OpenCustomerAddressViewDialogAction(this);
	}

	@Override
	protected String getLabelText() {
		return getEntity().getId().toString();
	}

}
