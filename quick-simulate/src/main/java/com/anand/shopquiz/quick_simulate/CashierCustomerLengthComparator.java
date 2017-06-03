package com.anand.shopquiz.quick_simulate;

import java.util.Comparator;

public  class CashierCustomerLengthComparator implements Comparator<Cashier> {

	public int compare(Cashier o1, Cashier o2) {
		//if pending count is identical choose the shorter counter
		if(o1.getPendingCustomers() == o2.getPendingCustomers()){
			return o1.getCashierId().compareTo(o2.getCashierId());
		}
		return o1.getPendingCustomers().compareTo(o2.getPendingCustomers());
	}

	
}
