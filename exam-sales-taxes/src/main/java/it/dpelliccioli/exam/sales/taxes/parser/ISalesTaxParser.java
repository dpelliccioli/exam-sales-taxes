/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.parser;

import java.util.List;

import it.dpelliccioli.exam.sales.taxes.model.CartItem;

/**
 * Mark an object which provide a list of CartItem
 *  
 * @author dpelliccioli
 * @see CartItem
 */
public interface ISalesTaxParser {

	/**
	 * Converts the input string list into a CartItem list
	 * 
	 * @param input - List of string that represents the orders
	 * @return A list of CartItem 
	 * @see CartItem
	 */
	public List<CartItem> parseSales(List<String> input);
	
}
