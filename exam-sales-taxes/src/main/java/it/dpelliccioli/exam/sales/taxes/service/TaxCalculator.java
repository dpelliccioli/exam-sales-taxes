package it.dpelliccioli.exam.sales.taxes.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.dpelliccioli.exam.sales.taxes.model.Product;
import it.dpelliccioli.exam.sales.taxes.util.RoundUtils;

/**
 * Provide business logic for tax calculation
 * 
 * @author dpelliccioli
 *
 */
@Service
public class TaxCalculator implements ITaxCalculator {

	@Value("${product.exclusion.list}")
	private List<String> exclusions;

	@Value("${product.sales.tax:0.10}")
	private BigDecimal salesTax;

	@Value("${product.import.tax:0.05}")
	private BigDecimal importTax;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal computeTaxes(Product product) {
		BigDecimal totalTaxes = BigDecimal.ZERO;
		if (product != null && product.getPrice() != null) {
			BigDecimal price = product.getPrice();
			
			BigDecimal saleTaxedPrice = BigDecimal.ZERO;
			BigDecimal impTaxedPrice = BigDecimal.ZERO;
			
			if (!exclusions.contains(product.getDescription())) {
				saleTaxedPrice = price.multiply(salesTax).setScale(2, RoundingMode.HALF_UP);
			}
			
			if(product.isImported()) {
				impTaxedPrice = price.multiply(importTax).setScale(2, RoundingMode.HALF_UP);
			}
			
			totalTaxes = RoundUtils.roundToNearest5(price.add(saleTaxedPrice).add(impTaxedPrice));
			
		}
		return totalTaxes;
	}

}
