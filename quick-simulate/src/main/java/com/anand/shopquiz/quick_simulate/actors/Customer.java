package com.anand.shopquiz.quick_simulate.actors;

import java.util.StringTokenizer;

import com.anand.shopquiz.quick_simulate.exceptions.InvalidConfigFileException;
import com.anand.shopquiz.quick_simulate.exceptions.NoItemsToBillException;

public class Customer {

	public enum CustomerType {
		A, B
	}

	private CustomerType type;
	private Integer arrivalMoment;
	private int numberofItems;
	private static int id = 0;
	private String objectIdentifier;

	public Customer(String serialRepresntation) throws InvalidConfigFileException {
		StringTokenizer st = new StringTokenizer(serialRepresntation);
		if (st.countTokens() != 3) {
			throw new InvalidConfigFileException("Expects Type arrivaltime noItems for all lines except first");
		}
		String typeStr = st.nextToken();
		String arrivalTime = st.nextToken();
		String noOfItemsStr = st.nextToken();

		if (typeStr.equalsIgnoreCase("A")) {
			type = CustomerType.A;
		} else if (typeStr.equalsIgnoreCase("B")) {
			type = CustomerType.B;
		} else {
			throw new InvalidConfigFileException("Customer Type should be A or B");
		}

		try {
			arrivalMoment = Integer.parseInt(arrivalTime);
			if (arrivalMoment < 0) {
				throw new InvalidConfigFileException(
						"Arrival Moment is Negetive. Sorry we are yet to invent time travel");
			}
		} catch (NumberFormatException e) {
			throw new InvalidConfigFileException("Arrival Time should be a Number (in Minutes)");
		}

		try {
			numberofItems = Integer.parseInt(noOfItemsStr);
			if (numberofItems <= 0) {
				throw new InvalidConfigFileException("Items too less. You need to buy Something");
			}
		} catch (NumberFormatException e) {
			throw new InvalidConfigFileException("NumberofItems should be a Whole Number above 1");
		}
		id++;
		objectIdentifier = Integer.toString(id);

	}

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

	public Integer getArrivalMoment() {
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
