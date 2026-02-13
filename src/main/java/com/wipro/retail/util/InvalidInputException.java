package com.wipro.retail.util;

public class InvalidInputException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
        super();
    }

    @Override
    public String toString() {
        return "Invalid Input";
    }
}
