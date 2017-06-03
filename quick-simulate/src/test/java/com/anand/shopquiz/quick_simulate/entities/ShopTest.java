package com.anand.shopquiz.quick_simulate.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import com.anand.shopquiz.quick_simulate.actors.Cashier;
import com.anand.shopquiz.quick_simulate.actors.Customer;
import com.anand.shopquiz.quick_simulate.actors.Customer.CustomerType;
import com.anand.shopquiz.quick_simulate.actors.ExpertCashier;

public class ShopTest {

	Shop shop;
	private Cashier cashier;

	@Before
	public void createSingleCashierShop() {
		Queue<Customer> customers = new LinkedList<Customer>();
		customers.add(new Customer(1, 2, CustomerType.A));
		customers.add(new Customer(2, 5, CustomerType.A));
		customers.add(new Customer(4, 5, CustomerType.A));
		customers.add(new Customer(4, 5, CustomerType.A));

		List<Cashier> cashiers = new ArrayList<Cashier>();
		cashier = new ExpertCashier();
		cashiers.add(cashier);
		shop = new Shop(cashiers, customers);
	}

	private void runClock(int end) {
		for (int i = 0; i <= end; i++) {
			shop.processClockTick(i);
		}
	}

	@Test
	public void testGetPendingCustomersStart() {
		runClock(1);
		assertEquals("After 1 sec there should be 1 pending customer", 1, (int) cashier.getPendingCustomers());
		assertEquals("After 1 sec there should be 2 pending items", 2, (int) cashier.getPendingItems());
		assertTrue(!shop.isDone());
	}

	@Test
	public void testGetPendingCustomersMore() {
		runClock(2);
		assertEquals("After 2 sec there should be 2 pending customer", 2, (int) cashier.getPendingCustomers());
		assertEquals("After 2 sec there should be 6 pending items", 6, (int) cashier.getPendingItems());
		assertTrue(!shop.isDone());
	}

	@Test
	public void testGetPendingCustomersAfterExit() {
		runClock(3);
		assertEquals("After 3 sec there should be 1 pending customer", 1, (int) cashier.getPendingCustomers());
		assertEquals("After 3 sec there should be 10 pending items", 5, (int) cashier.getPendingItems());
		assertTrue(!shop.isDone());
	}

	@Test
	public void testGetPendingCustomersAfter14() {
		runClock(14);
		assertEquals("After 14 sec there should be 1 pending customer", 1, (int) cashier.getPendingCustomers());
		assertEquals("After 14 sec there should be 4 pending items", 4, (int) cashier.getPendingItems());
		assertTrue(!shop.isDone());
	}

	@Test
	public void testGetPendingCustomersAfterFullExecution() {
		runClock(18);
		assertEquals("After 14 sec there should be 0 pending customer", 0, (int) cashier.getPendingCustomers());
		assertEquals("After 14 sec there should be 0 pending items", 0, (int) cashier.getPendingItems());
		assertTrue(shop.isDone());
	}

	@Test
	public void testGetPendingCustomersAfterOvershoot() {
		runClock(19);
		assertEquals("After 18 sec there should be 0 pending customer", 0, (int) cashier.getPendingCustomers());
		assertEquals("After 18 sec there should be 0 pending items", 0, (int) cashier.getPendingItems());
		assertTrue(shop.isDone());
	}

}
