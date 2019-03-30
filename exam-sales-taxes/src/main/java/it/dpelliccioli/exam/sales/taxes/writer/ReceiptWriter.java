package it.dpelliccioli.exam.sales.taxes.writer;

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

	private static final String EMPTY_STRING = "";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String write(Receipt receipt) {

		String ret = EMPTY_STRING;

		if (receipt != null) {
			StringBuffer stringBuffer = new StringBuffer("\n\r");
			stringBuffer.append("======= RECEIPT ======= \n\r");
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

			stringBuffer.append("\n\r");
			stringBuffer.append("Total (taxes included): ");
			stringBuffer.append(receipt.getAmount());
			stringBuffer.append("\n\r======================= \n\r");

			ret = stringBuffer.toString();
		}
		return ret;

	}

}
