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
		cashier = new ExpertCashier();
		customers = new LinkedList<Customer>();
		customers.add(new Customer(1, 2, CustomerType.A));
		customers.add(new Customer(2, 5, CustomerType.A));
		customers.add(new Customer(4, 5, CustomerType.A));
	}

	public void createSimultaneousCustomerCashier() {
		cashier = new ExpertCashier();
		customers = new LinkedList<Customer>();
		customers.add(new Customer(1, 2, CustomerType.A));
		customers.add(new Customer(2, 3, CustomerType.A));
		customers.add(new Customer(2, 4, CustomerType.A));
	}
	
	public void createSimultaneousCustomerTraineeCashier() {
		cashier = new TraineeCashier();
		customers = new LinkedList<Customer>();
		customers.add(new Customer(1, 2, CustomerType.A));
		customers.add(new Customer(2, 3, CustomerType.A));
		customers.add(new Customer(2, 4, CustomerType.A));
	}

	private void runClock(int duration) {
		for (int i = 0; i <= duration; i++) {
			cashier.processClockTick(i);
			Customer aCustomer = null;
			while ((aCustomer = customers.peek()) != null && (aCustomer.arrivalMoment == i)) {
				customers.remove();
				cashier.addCustomer(aCustomer);
			}
			customers.peek();

			cashier.dump();
		}
	}

	@Test
	public void testPendingCustomersStart() {
		runClock(1);
		assertEquals("After 1 sec there should be 1 pending customer", 1, (int) cashier.pendingCustomers);
		assertEquals("After 1 sec there should be 2 pending items", 2, (int) cashier.normalizedPendingItems);
	}

	@Test
	public void testPendingCustomersMore() {
		runClock(2);
		assertEquals("After 2 sec there should be 2 pending customer", 2, (int) cashier.pendingCustomers);
		assertEquals("After 2 sec there should be 6 pending items", 6, (int) cashier.normalizedPendingItems);
	}

	@Test
	public void testPendingCustomersAfterExit() {
		runClock(3);
		assertEquals("After 3 sec there should be 1 pending customer", 1, (int) cashier.pendingCustomers);
		assertEquals("After 3 sec there should be 10 pending items", 5, (int) cashier.normalizedPendingItems);
	}

	@Test
	public void testPendingCustomersAfterFullExecution() {
		runClock(14);
		assertEquals("After 14 sec there should be 0 pending customer", 0, (int) cashier.pendingCustomers);
		assertEquals("After 14 sec there should be 0 pending items", 0, (int) cashier.normalizedPendingItems);
	}

	@Test
	public void testPendingCustomersAfterOvershoot() {
		runClock(15);
		assertEquals("After 15 sec there should be 0 pending customer", 0, (int) cashier.pendingCustomers);
		assertEquals("After 15 sec there should be 0 pending items", 0, (int) cashier.normalizedPendingItems);
	}

	@Test
	public void testSimultaneousCustomers2() {
		createSimultaneousCustomerCashier();
		runClock(2);
		assertEquals("After 2 sec there should be 3 pending customer", 3, (int) cashier.pendingCustomers);
		assertEquals("After 2 sec there should be 8 pending items", 8, (int) cashier.normalizedPendingItems);
		
	}
	
	@Test
	public void testSimultaneousCustomers4() {
		createSimultaneousCustomerCashier();
		runClock(4);
		assertEquals("After 4 sec there should be 3 pending customer", 2, (int) cashier.pendingCustomers);
		assertEquals("After 4 sec there should be 6 pending items", 6, (int) cashier.normalizedPendingItems);
		
	}
	
	@Test
	public void testSimultaneousCustomers10() {
		createSimultaneousCustomerCashier();
		runClock(10);
		assertEquals("After 10 sec there should be 0 pending customer", 0, (int) cashier.pendingCustomers);
		assertEquals("After 10 sec there should be 0 pending items", 0, (int) cashier.normalizedPendingItems);
		
	}
	
	
	@Test
	public void testTraineeSimultaneousCustomers4() {
		createSimultaneousCustomerTraineeCashier();
		runClock(4);
		assertEquals("After 4 sec there should be 3 pending customer", 3, (int) cashier.pendingCustomers);
		assertEquals("After 4 sec there should be 8 pending items", 15, (int) cashier.normalizedPendingItems);
		
	}
	
	@Test
	public void testTraineeSimultaneousCustomers8() {
		createSimultaneousCustomerTraineeCashier();
		runClock(8);
		assertEquals("After 8 sec there should be 2 pending customer", 2, (int) cashier.pendingCustomers);
		assertEquals("After 8 sec there should be 11 pending items", 11, (int) cashier.normalizedPendingItems);
		
	}
	
	@Test
	public void testTraineeSimultaneousCustomers19() {
		createSimultaneousCustomerTraineeCashier();
		runClock(19);
		assertEquals("After 19 sec there should be 0 pending customer", 0, (int) cashier.pendingCustomers);
		assertEquals("After 19 sec there should be 0 pending items", 0, (int) cashier.normalizedPendingItems);
		
	}
	

}
