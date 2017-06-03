package com.anand.shopquiz.quick_simulate.utils;

import java.util.Comparator;

import com.anand.shopquiz.quick_simulate.actors.Cashier;

public class CashierPendingItemsComparator implements Comparator<Cashier> {

	public int compare(Cashier o1, Cashier o2) {
		// if pending items is identical choose the shorter counter
		if (o1.getPendingItems() == o2.getPendingItems()) {
			return o1.getCashierId().compareTo(o2.getCashierId());
		}
		return o1.getPendingItems().compareTo(o2.getPendingItems());
	}

}
