package com.anand.shopquiz.quick_simulate;

public class TraineeCashier extends Cashier {

	
	
	public TraineeCashier(){
		name = "t-cashier";
		delayFactor = 2;
	}
	
	public void addCustomer(Customer aCustomer) {
		logger.trace("Customer added " + aCustomer);
		customers.add(aCustomer);
		pendingCustomers++;
		normalizedPendingItems += (aCustomer.getNumberofItems()*delayFactor);
	}
}
