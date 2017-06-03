package com.anand.shopquiz.quick_simulate;

public class ExpertCashier extends Cashier {
	protected int delayFactor = 1;
			
	public ExpertCashier(){
		name = "e-cashier";
	}
	public void addCustomer(Customer aCustomer) {
		logger.trace("Customer added " + aCustomer);
		customers.add(aCustomer);
		pendingCustomers++;
		normalizedPendingItems += aCustomer.getNumberofItems();
	}
}
