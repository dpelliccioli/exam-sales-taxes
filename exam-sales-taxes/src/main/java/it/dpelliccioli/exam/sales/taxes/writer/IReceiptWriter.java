package it.dpelliccioli.exam.sales.taxes.writer;

import it.dpelliccioli.exam.sales.taxes.model.Receipt;

/**
 * Define methods for receipt writer
 * 
 * @author dpelliccioli
 * 
 */
public interface IReceiptWriter {
	
	/**
	 * Provide a string representation of the input Receipt
	 * 
	 * @param receipt - The receipt to print
	 * @return A string representation of Receipt
	 * @see Receipt
	 */
	public String write(Receipt receipt);

}
