package it.dpelliccioli.exam.sales.taxes.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

/**
 * Represent a single item of the receipt
 * 
 * @author dpelliccioli
 * @see Receipt
 */
@Builder
@Data
public class ReceiptItem {

	private String description;

	private BigDecimal amount;

	private int quantity;

	private boolean imported;

}
