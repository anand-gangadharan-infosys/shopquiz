package com.anand.shopquiz.quick_simulate.utils;

import java.util.Comparator;

import com.anand.shopquiz.quick_simulate.actors.Customer;

public class CustomerArrivalComparator implements Comparator<Customer> {

	public int compare(Customer o1, Customer o2) {
		
		return o1.getArrivalMoment().compareTo(o2.getArrivalMoment());
	}

}
