package it.dpelliccioli.exam.sales.taxes.examsalestaxes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import it.dpelliccioli.exam.sales.taxes.examsalestaxes.config.TestConfig;
import it.dpelliccioli.exam.sales.taxes.model.Receipt;
import it.dpelliccioli.exam.sales.taxes.model.ReceiptItem;
import it.dpelliccioli.exam.sales.taxes.writer.IReceiptWriter;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ReceiptWriterTest {

	@Autowired
	private IReceiptWriter writer;

	@Test
	public void nullReceiptTest() {
		assertEquals(StringUtils.EMPTY, writer.write(null));
	}

	@Test
	public void emptyReceiptTest() {
		String ret = writer.write(Receipt.builder().build());
		assertNotNull(ret);
		assertTrue(ret.contains("RECEIPT"));
		assertTrue(ret.contains("Total (taxes included)"));
	}

	@Test
	public void amountReceiptTest() {
		String ret = writer.write(Receipt.builder().amount(new BigDecimal("12.45")).build());
		assertNotNull(ret);
		assertTrue(ret.contains("12.45"));
	}

	@Test
	public void fullReceiptTest() {

		String ret = writer.write(Receipt
				.builder().amount(new BigDecimal("12.45")).items(Arrays.asList(new ReceiptItem[] { ReceiptItem.builder()
						.amount(new BigDecimal("5.50")).description("book").imported(true).quantity(1).build() }))
				.build());
		assertNotNull(ret);
		assertTrue(ret.contains("1"));
		assertTrue(ret.contains("book"));
		assertTrue(ret.contains("imported"));
		assertTrue(ret.contains("5.50"));
		assertTrue(ret.contains("12.45"));
	}

}
