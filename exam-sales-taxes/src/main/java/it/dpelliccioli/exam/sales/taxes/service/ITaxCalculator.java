/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.service;

import java.math.BigDecimal;

import it.dpelliccioli.exam.sales.taxes.model.Product;

/**
 * Define methods for tax calculator
 * 
 * @author dpelliccioli
 *
 */
public interface ITaxCalculator {

	/**
	 * Update product price with taxes
	 * 
	 * @param product - The input product
	 * @return Product price updated with taxes
	 */
	public BigDecimal computeTaxes(Product product);
	
}
