/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author dpelliccioli
 *
 */

@Builder
@Data
public class CartItem {

	private int quantity;
	
	private Product item;
	
}
