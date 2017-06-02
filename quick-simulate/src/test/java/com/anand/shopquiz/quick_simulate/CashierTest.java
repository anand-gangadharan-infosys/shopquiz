package com.anand.shopquiz.quick_simulate;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import com.anand.shopquiz.quick_simulate.Customer.CustomerType;

public class CashierTest {

	Cashier cashier;

	
	static Queue<Customer> customers;

	@Before
	public void createCashier() {
		cashier = new Cashier();
		customers = new LinkedList<Customer>();
		customers.add(new Customer(1, 2, CustomerType.A));
		customers.add(new Customer(2, 5, CustomerType.A));
		customers.add(new Customer(4, 5, CustomerType.A));
	}

	private void runClock(int end) {
		
		for (int i = 0; i <= end; i++) {
			cashier.processClockTick(i);
			Customer aCustomer = customers.peek();
			if (aCustomer!= null && aCustomer.arrivalMoment == i) {
				customers.remove();
				cashier.addCustomer(aCustomer);
			}
			cashier.dump();
		}
	}

	@Test
	public void testPendingCustomersStart() {
		runClock(1);
		assertEquals("After 1 sec there should be 1 pending customer", 1, (int) cashier.pendingCustomers);
		assertEquals("After 1 sec there should be 2 pending items", 2, (int) cashier.pendingItems);
	}

	@Test
	public void testPendingCustomersMore() {
		runClock(2);
		assertEquals("After 2 sec there should be 2 pending customer", 2, (int) cashier.pendingCustomers);
		assertEquals("After 2 sec there should be 6 pending items", 6, (int) cashier.pendingItems);
	}

	@Test
	public void testPendingCustomersAfterExit() {
		runClock(3);
		assertEquals("After 3 sec there should be 1 pending customer", 1, (int) cashier.pendingCustomers);
		assertEquals("After 3 sec there should be 10 pending items", 5, (int) cashier.pendingItems);
	}
	
	@Test
	public void testPendingCustomersAfterFullExecution() {
		runClock(14);
		assertEquals("After 14 sec there should be 0 pending customer", 0, (int) cashier.pendingCustomers);
		assertEquals("After 14 sec there should be 0 pending items", 0, (int) cashier.pendingItems);
	}
	
	@Test
	public void testPendingCustomersAfterOvershoot() {
		runClock(15);
		assertEquals("After 15 sec there should be 0 pending customer", 0, (int) cashier.pendingCustomers);
		assertEquals("After 15 sec there should be 0 pending items", 0, (int) cashier.pendingItems);
	}

}
