/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.parser;

import java.util.List;

import it.dpelliccioli.exam.sales.taxes.model.CartItem;

/**
 * @author Administrator
 *
 */
public interface ISalesTaxParser {

	public List<CartItem> parseSales(List<String> input);
	
}
