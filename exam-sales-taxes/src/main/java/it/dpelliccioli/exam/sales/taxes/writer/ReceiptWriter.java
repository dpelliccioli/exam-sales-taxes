package it.dpelliccioli.exam.sales.taxes.writer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import it.dpelliccioli.exam.sales.taxes.model.Receipt;

/**
 * Provide a string representation of Receipt
 * 
 * @author dpelliccioli
 * @see Receipt
 *
 */
@Component
public class ReceiptWriter implements IReceiptWriter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String write(Receipt receipt) {

		String ret = StringUtils.EMPTY;

		if (receipt != null) {
			StringBuilder stringBuilder = new StringBuilder("\n\r");
			stringBuilder.append("======= RECEIPT ======= \n\r");
			
			if (receipt.getItems() != null && !receipt.getItems().isEmpty()) {
				receipt.getItems().forEach(x -> {
					stringBuilder.append(x.getQuantity());
					stringBuilder.append(" ");
					if (x.isImported()) {
						stringBuilder.append("imported ");
					}
					stringBuilder.append(x.getDescription());
					stringBuilder.append(": ");
					stringBuilder.append(x.getAmount());
					stringBuilder.append("\n\r");
				});
			}

			stringBuilder.append("\n\r");
			stringBuilder.append("Total (taxes included): ");
			stringBuilder.append(receipt.getAmount());
			stringBuilder.append("\n\r======================= \n\r");

			ret = stringBuilder.toString();
		}
		return ret;

	}

}
