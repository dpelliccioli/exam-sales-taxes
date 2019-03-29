/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.service;

import java.math.BigDecimal;

import it.dpelliccioli.exam.sales.taxes.model.Product;

/**
 * @author Administrator
 *
 */
public interface ITaxCalculator {

	public BigDecimal computeTaxes(Product product);
	
}
