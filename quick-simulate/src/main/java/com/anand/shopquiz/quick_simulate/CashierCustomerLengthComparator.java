package com.anand.shopquiz.quick_simulate;

import java.util.Comparator;

public  class CashierCustomerLengthComparator implements Comparator<Cashier> {

	public int compare(Cashier o1, Cashier o2) {
		//if pending count is identical choose the shorter counter
		if(o1.pendingCustomers == o2.pendingCustomers){
			return o1.cashierId.compareTo(o2.cashierId);
		}
		return o1.pendingCustomers.compareTo(o2.pendingCustomers);
	}

	
}
