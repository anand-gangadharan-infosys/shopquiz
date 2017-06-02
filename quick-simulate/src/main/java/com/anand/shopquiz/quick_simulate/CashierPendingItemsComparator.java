package com.anand.shopquiz.quick_simulate;

import java.util.Comparator;

public class CashierPendingItemsComparator implements Comparator<Cashier> {

	public int compare(Cashier o1, Cashier o2) {
		// if pending items is identical choose the shorter counter
		if (o1.pendingItems == o2.pendingItems) {
			return o1.cashierId.compareTo(o2.cashierId);
		}
		return o1.pendingItems.compareTo(o2.pendingItems);
	}

}
