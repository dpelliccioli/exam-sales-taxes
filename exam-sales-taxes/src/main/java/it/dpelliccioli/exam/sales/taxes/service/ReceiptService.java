package it.dpelliccioli.exam.sales.taxes.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dpelliccioli.exam.sales.taxes.model.CartItem;
import it.dpelliccioli.exam.sales.taxes.model.Product;
import it.dpelliccioli.exam.sales.taxes.model.Receipt;
import it.dpelliccioli.exam.sales.taxes.model.ReceiptItem;

/**
 * Compute receipt with orders and tax data
 * 
 * @author dpelliccioli
 *
 */
@Service
public class ReceiptService implements IReceiptService{

	@Autowired
	private ITaxCalculator taxCalculator;

	/**
	 * {@inheritDoc}
	 */
	public Receipt computeReceipt(List<CartItem> items) {

		Receipt receipt = null;

		if (items != null && !items.isEmpty() && taxCalculator != null) {

			List<ReceiptItem> receiptItems = items.stream()
					.map(x -> ReceiptItem.builder().description(x.getItem().getDescription()).quantity(x.getQuantity())
							.amount(new BigDecimal(x.getQuantity()).multiply(computeAmount(x.getItem())))
							.imported(x.getItem().isImported()).build())
					.collect(Collectors.toList());

			receipt = Receipt.builder().items(receiptItems)
					.amount(receiptItems.stream()
							.map(item -> item.getAmount() != null ? item.getAmount() : BigDecimal.ZERO)
							.reduce((x, y) -> x.add(y)).get())
					.build();

		}

		return receipt;
	}

	/**
	 * Update product price with taxes
	 * 
	 * @param product - The product on which calculate taxes
	 * @return The updated price with taxes
	 */
	private BigDecimal computeAmount(Product product) {
		BigDecimal tax = BigDecimal.ZERO;

		if (product != null) {
			tax = taxCalculator.computeTaxes(product);
		}

		return tax;
	}

}
