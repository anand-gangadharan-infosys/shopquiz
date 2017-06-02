package com.anand.shopquiz.quick_simulate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anand.shopquiz.quick_simulate.Customer.CustomerType;

public class Shop {

	private static final Logger logger = LogManager.getLogger(Shop.class);

	List<Cashier> cashiers = new ArrayList<Cashier>();
	
	//Sorted chronologically
	Queue<Customer> walkinCustomers = new LinkedList<Customer>();

	public Shop(List<Cashier> cashiers, Queue<Customer> customers) {
		this.cashiers = cashiers;
		this.walkinCustomers = customers;
	}

	public void processClockTick(int tick) {
		advanceCashierQueues(tick);
		enqueNewCustomers(tick);
	}

	private void enqueNewCustomers(int tick) {
		while (walkinCustomers.peek() != null && walkinCustomers.peek().arrivalMoment == tick) {
			Customer aCustomer = walkinCustomers.remove();
			Cashier chosenCashier = findPreferedCashier(aCustomer);
			chosenCashier.addCustomer(aCustomer);
		}

	}

	private Cashier findPreferedCashier(Customer aCustomer) {
		Cashier chosenCashier = null;
		if (aCustomer.getType() == CustomerType.A) {
			chosenCashier = findShortestLineCashier();
		} else if (aCustomer.getType() == CustomerType.B) {
			chosenCashier = findLowestItemCashier();
		} else {
			return null;
		}
		return chosenCashier;
	}

	private void advanceCashierQueues(int tick) {
		for (Iterator<Cashier> iterator = cashiers.iterator(); iterator.hasNext();) {
			Cashier cashier = iterator.next();
			cashier.processClockTick(tick);
		}
	}

	private Cashier findShortestLineCashier() {
		cashiers.sort(new CashierCustomerLengthComparator());
		return cashiers.get(0);
	}

	private Cashier findLowestItemCashier() {
		cashiers.sort(new CashierPendingItemsComparator());
		return cashiers.get(0);
	}

	public boolean isDone() {
		if (walkinCustomers.isEmpty()) {
			// Not done if any cashiers have pending customers.
			for (Iterator<Cashier> iterator = cashiers.iterator(); iterator.hasNext();) {
				Cashier cashier = iterator.next();
				return (cashier.pendingCustomers > 0);
			}
		}
		return true;
	}

}
