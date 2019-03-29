/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

/**
 * @author Administrator
 *
 */

@Builder
@Data
public class ReceiptItem {

	private String description;
	
	private BigDecimal amount;
	
	private int quantity;
	
	private boolean imported;

}
