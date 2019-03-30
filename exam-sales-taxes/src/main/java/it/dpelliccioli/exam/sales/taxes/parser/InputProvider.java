package it.dpelliccioli.exam.sales.taxes.parser;

import java.util.List;

/**
 * Mark an object that provide an input with orders
 * 
 * @author dpelliccioli
 *
 */
public interface InputProvider {
	
	/**
	 * Create a list of input orders
	 * 
	 * @param args - Array of string with parameters
	 * @return - List of string with orders
	 */
	public List<String> createInput(String[] args);

}
