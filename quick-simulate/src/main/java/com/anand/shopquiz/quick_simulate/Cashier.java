package com.anand.shopquiz.quick_simulate;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cashier {
	
	Integer pendingItems = 0;
	
	Integer pendingCustomers = 0;
	
	int currentTime = 0;
	
	Queue<Customer> customers = new LinkedList<Customer>();
	
	static int id = 0;
	
	Integer cashierId;
	
	
	private static final Logger logger = LogManager.getLogger(Cashier.class);
	
		
	public Cashier(){
		id++;
		cashierId = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Cashier-"+cashierId;
	}
	
	
	public void addCustomer(Customer aCustomer){
		logger.trace("Customer added "+aCustomer);
		customers.add(aCustomer);
		pendingCustomers++;
		pendingItems += aCustomer.getNumberofItems();
	}
	
	public void processClockTick(int tick) {
		currentTime = tick;
		
		if(!customers.isEmpty()){
			Customer aCustomer = customers.peek();
			try {
				pendingItems--;
				aCustomer.billOneItem();
			} catch (NoItemsToBillException e) {
				customers.remove();
				pendingCustomers--;
			}
		}
	}
	
	public void dump(){
		logger.trace(this+" tick="+currentTime+" Pending Cus="+pendingCustomers+" Pending Items="+pendingItems);
	}
	
	
}
