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
			StringBuffer stringBuffer = new StringBuffer("\n\r");
			stringBuffer.append("======= RECEIPT ======= \n\r");
			
			if (receipt.getItems() != null && !receipt.getItems().isEmpty()) {
				receipt.getItems().forEach(x -> {
					stringBuffer.append(x.getQuantity());
					stringBuffer.append(" ");
					if (x.isImported()) {
						stringBuffer.append("imported ");
					}
					stringBuffer.append(x.getDescription());
					stringBuffer.append(": ");
					stringBuffer.append(x.getAmount());
					stringBuffer.append("\n\r");
				});
			}

			stringBuffer.append("\n\r");
			stringBuffer.append("Total (taxes included): ");
			stringBuffer.append(receipt.getAmount());
			stringBuffer.append("\n\r======================= \n\r");

			ret = stringBuffer.toString();
		}
		return ret;

	}

}
