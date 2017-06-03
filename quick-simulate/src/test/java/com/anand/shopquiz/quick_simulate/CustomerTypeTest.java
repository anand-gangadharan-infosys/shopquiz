package com.anand.shopquiz.quick_simulate;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import com.anand.shopquiz.quick_simulate.Customer.CustomerType;

public class CustomerTypeTest {

	Shop shop;

	Cashier cashier1;
	Cashier cashier2;
	Cashier cashier3;
	Cashier cashier4;
	Cashier cashier5;

	List<Cashier> cashiers;

	private void runClock(int end) {
		for (int i = 0; i <= end; i++) {
			shop.processClockTick(i);
		}
	}

	@Before
	public void createMockedCashiers() {
		cashiers = new ArrayList<Cashier>();

		cashier1 = new ExpertCashier();
		cashier2 = new ExpertCashier();
		cashier3 = new ExpertCashier();
		cashier4 = new ExpertCashier();
		cashier5 = new ExpertCashier();
		

		cashiers.add(cashier1);
		cashiers.add(cashier2);
		cashiers.add(cashier3);
		cashiers.add(cashier4);
		cashiers.add(cashier5);

	}

	@Test
	public void testFirstCustomerTypeSimultaneousA() {
		Queue<Customer> customers = new LinkedList<Customer>();
		customers.add(new Customer(1, 10, CustomerType.A));
		customers.add(new Customer(1, 10, CustomerType.A));
		customers.add(new Customer(1, 10, CustomerType.A));
		customers.add(new Customer(1, 10, CustomerType.A));
		customers.add(new Customer(1, 10, CustomerType.A));
		
		shop = new Shop(cashiers, customers);
		runClock(5);
		
		assertEquals( 1,(int)cashier1.getPendingCustomers());
		assertEquals( 1,(int)cashier2.getPendingCustomers());
		assertEquals( 1,(int)cashier3.getPendingCustomers());
		assertEquals( 1,(int)cashier4.getPendingCustomers());
		assertEquals( 1,(int)cashier5.getPendingCustomers());
		
		assertEquals( 6,(int)cashier1.getNormalizedPendingItems());
		assertEquals( 6,(int)cashier2.getNormalizedPendingItems());
		assertEquals( 6,(int)cashier3.getNormalizedPendingItems());
		assertEquals( 6,(int)cashier4.getNormalizedPendingItems());
		assertEquals( 6,(int)cashier5.getNormalizedPendingItems());
	}
	
	@Test
	public void testFirstCustomerTypeASequential() {
		Queue<Customer> customers = new LinkedList<Customer>();
		customers.add(new Customer(1, 10, CustomerType.A));
		customers.add(new Customer(2, 10, CustomerType.A));
		customers.add(new Customer(3, 10, CustomerType.A));
		customers.add(new Customer(4, 10, CustomerType.A));
		customers.add(new Customer(5, 10, CustomerType.A));
		
		shop = new Shop(cashiers, customers);
		runClock(5);
		
		assertEquals( 1,(int)cashier1.getPendingCustomers());
		assertEquals( 1,(int)cashier2.getPendingCustomers());
		assertEquals( 1,(int)cashier3.getPendingCustomers());
		assertEquals( 1,(int)cashier4.getPendingCustomers());
		assertEquals( 1,(int)cashier5.getPendingCustomers());
		
		assertEquals( 6,(int)cashier1.getNormalizedPendingItems());
		assertEquals( 7,(int)cashier2.getNormalizedPendingItems());
		assertEquals( 8,(int)cashier3.getNormalizedPendingItems());
		assertEquals( 9,(int)cashier4.getNormalizedPendingItems());
		assertEquals( 10,(int)cashier5.getNormalizedPendingItems());
	}
	
	@Test
	public void testFirstCustomerTypeABSimulatneous() {
		Queue<Customer> customers = new LinkedList<Customer>();
		customers.add(new Customer(1, 10, CustomerType.A));
		customers.add(new Customer(1, 11, CustomerType.A));
		customers.add(new Customer(1, 12, CustomerType.A));
		customers.add(new Customer(1, 13, CustomerType.A));
		customers.add(new Customer(1, 14, CustomerType.A));
		customers.add(new Customer(1, 10, CustomerType.B));
		customers.add(new Customer(1, 10, CustomerType.B));
		
		shop = new Shop(cashiers, customers);
		runClock(5);
		
		assertEquals( 2,(int)cashier1.getPendingCustomers());
		assertEquals( 2,(int)cashier2.getPendingCustomers());
		assertEquals( 1,(int)cashier3.getPendingCustomers());
		assertEquals( 1,(int)cashier4.getPendingCustomers());
		assertEquals( 1,(int)cashier5.getPendingCustomers());
		
		assertEquals( 16,(int)cashier1.getNormalizedPendingItems());
		assertEquals( 17,(int)cashier2.getNormalizedPendingItems());
		assertEquals( 8,(int)cashier3.getNormalizedPendingItems());
		assertEquals( 9,(int)cashier4.getNormalizedPendingItems());
		assertEquals( 10,(int)cashier5.getNormalizedPendingItems());
	}

}
