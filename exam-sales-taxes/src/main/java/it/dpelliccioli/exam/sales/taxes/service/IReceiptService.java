package it.dpelliccioli.exam.sales.taxes.service;

import java.util.List;

import it.dpelliccioli.exam.sales.taxes.model.CartItem;
import it.dpelliccioli.exam.sales.taxes.model.Receipt;

/**
 * Define methods for compute a receipt
 * 
 * @author dpelliccioli
 *
 */
public interface IReceiptService {

	/**
	 * Compute a receipt
	 * 
	 * @param items - CartItem list to process
	 * @return A receipt
	 * @see Receipt
	 * @see CartItem
	 */
	public Receipt computeReceipt(List<CartItem> items);
	
}
