package com.anand.shopquiz.quick_simulate.actors;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anand.shopquiz.quick_simulate.exceptions.NoItemsToBillException;

/**
 * Cashier of the Shop. Note that a pending items is maintained at cashier level
 * too.
 * 
 * @author anand_gangadharan
 *
 */
public class Cashier {

	private int currentTime = 0;
	private static int id = 0;
	private Integer cashierId;

	protected int delayFactor = 1;
	protected Integer pendingItems = 0;
	protected Integer pendingCustomers = 0;
	protected Queue<Customer> customers = new LinkedList<Customer>();
	protected String name;
	protected static final Logger logger = LogManager.getLogger(Cashier.class);

	public Cashier() {
		id++;
		cashierId = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + cashierId;
	}

	public void addCustomer(Customer aCustomer) {
		logger.trace("Customer added " + aCustomer);
		customers.add(aCustomer);
		pendingCustomers++;
		pendingItems += aCustomer.getNumberofItems();
	}
	
	public void processClockTick(int tick) {
		currentTime = tick;

		if (!customers.isEmpty()) {
			Customer aCustomer = customers.peek();
			try {

				if (delayLatch()) {
					pendingItems--;
					aCustomer.billOneItem();
				}
			} catch (NoItemsToBillException e) {
				customers.remove();
				pendingCustomers--;
			}
		}
	}

	public void dump() {
		logger.trace(this + " tick=" + currentTime + " id =" + getCashierId() + " Pending Cus=" + getPendingCustomers()
				+ " Pending Items=" + getPendingItems());
	}

	public Integer getPendingCustomers() {
		return pendingCustomers;
	}

	public Integer getPendingItems() {
		return pendingItems;
	}

	public Integer getCashierId() {
		return cashierId;
	}

	int delaybuffer = 0;

	private boolean delayLatch() {
		delaybuffer++;
		if (delaybuffer == delayFactor) {
			delaybuffer = 0;
			return true;
		}
		return false;
	}
}
