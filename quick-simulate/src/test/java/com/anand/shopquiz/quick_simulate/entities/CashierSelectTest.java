package com.anand.shopquiz.quick_simulate.entities;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import com.anand.shopquiz.quick_simulate.actors.Cashier;
import com.anand.shopquiz.quick_simulate.actors.Customer;
import com.anand.shopquiz.quick_simulate.actors.Customer.CustomerType;

public class CashierSelectTest {

	Shop shop;

	Cashier cashier1;
	Cashier cashier2;
	Cashier cashier3;
	Cashier cashier4;
	Cashier cashier5;

	@Before
	public void createMockedCashiers() {
		List<Cashier> cashiers = new ArrayList<Cashier>();

		cashier1 = mock(Cashier.class);
		when(cashier1.getPendingCustomers()).thenReturn(3);
		when(cashier1.getNormalizedPendingItems()).thenReturn(5);
		when(cashier1.getCashierId()).thenReturn(1);

		cashier2 = mock(Cashier.class);
		when(cashier2.getPendingCustomers()).thenReturn(2);
		when(cashier2.getNormalizedPendingItems()).thenReturn(10);
		when(cashier2.getCashierId()).thenReturn(2);

		cashier3 = mock(Cashier.class);
		when(cashier3.getPendingCustomers()).thenReturn(5);
		when(cashier3.getNormalizedPendingItems()).thenReturn(8);
		when(cashier3.getCashierId()).thenReturn(3);

		cashier4 = mock(Cashier.class);
		when(cashier4.getPendingCustomers()).thenReturn(2);
		when(cashier4.getNormalizedPendingItems()).thenReturn(12);
		when(cashier4.getCashierId()).thenReturn(4);

		cashier5 = mock(Cashier.class);
		when(cashier5.getPendingCustomers()).thenReturn(4);
		when(cashier5.getNormalizedPendingItems()).thenReturn(5);
		when(cashier5.getCashierId()).thenReturn(5);

		Queue<Customer> customers = new LinkedList<Customer>();
		customers.add(new Customer(1, 2, CustomerType.A));

		cashiers.add(cashier1);
		cashiers.add(cashier2);
		cashiers.add(cashier3);
		cashiers.add(cashier4);
		cashiers.add(cashier5);

		shop = new Shop(cashiers, customers);

	}

	@Test
	public void testFindShortestQueueCashier() {
		assertEquals(cashier1, shop.findLowestItemCashier());

	}

	@Test
	public void testFindShortestItemCashier() {
		assertEquals(cashier2, shop.findShortestLineCashier());

	}

}
