/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.writer;

import it.dpelliccioli.exam.sales.taxes.model.Receipt;

/**
 * @author Administrator
 *
 */
public interface IReceiptWriter {
	
	public String write(Receipt receipt);

}
