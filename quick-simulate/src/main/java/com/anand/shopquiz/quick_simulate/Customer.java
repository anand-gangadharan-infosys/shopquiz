package com.anand.shopquiz.quick_simulate;

public class Customer {

	enum CustomerType {
		A, B
	}

	CustomerType type;

	int arrivalMoment;

	int numberofItems;

	static int id = 0;

	String objectIdentifier;

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
