package it.dpelliccioli.exam.sales.taxes.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

/**
 * Represent a product
 * 
 * @author dpelliccioli
 *
 */
@Builder
@Data
public class Product {

	private String description;

	private BigDecimal price;

	private boolean imported;

}
