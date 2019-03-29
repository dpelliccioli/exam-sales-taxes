/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * @author dpelliccioli
 *
 */

@Builder
@Data
public class Receipt {

	private List<ReceiptItem> items;
	
	private BigDecimal amount;

}
