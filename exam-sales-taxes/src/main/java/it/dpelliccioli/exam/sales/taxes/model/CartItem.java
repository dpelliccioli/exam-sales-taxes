/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.model;

import lombok.Builder;
import lombok.Data;

/**
 * Represent an item in the cart orders
 * 
 * @author dpelliccioli
 *
 */
@Builder
@Data
public class CartItem {

	private int quantity;

	private Product item;

}
