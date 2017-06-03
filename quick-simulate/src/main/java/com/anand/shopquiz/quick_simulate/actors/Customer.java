package com.anand.shopquiz.quick_simulate.actors;

import com.anand.shopquiz.quick_simulate.exceptions.NoItemsToBillException;

public class Customer {

	public enum CustomerType {
		A, B
	}

	private CustomerType type;
	private int arrivalMoment;
	private int numberofItems;
	private static int id = 0;
	private String objectIdentifier;

	public Customer(int arrivalMoment, int numberofItems, CustomerType type) {
		id++;
		objectIdentifier = Integer.toString(id);
		this.arrivalMoment = arrivalMoment;
		this.numberofItems = numberofItems;
		this.type = type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Customer-" + objectIdentifier;
	}

	public int getNumberofItems() {
		return numberofItems;
	}

	public int getArrivalMoment() {
		return arrivalMoment;
	}

	public CustomerType getType() {
		return type;
	}

	public void setArrivalMoment(int arrivalMoment) {
		this.arrivalMoment = arrivalMoment;
	}

	public void setNumberofItems(int numberofItems) {
		this.numberofItems = numberofItems;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public void billOneItem() throws NoItemsToBillException {
		numberofItems--;
		if (numberofItems == 0) {
			throw new NoItemsToBillException();
		}
	}

}
