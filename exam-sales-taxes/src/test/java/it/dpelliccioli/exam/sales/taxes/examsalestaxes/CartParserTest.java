package it.dpelliccioli.exam.sales.taxes.examsalestaxes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import it.dpelliccioli.exam.sales.taxes.examsalestaxes.config.TestConfig;
import it.dpelliccioli.exam.sales.taxes.model.CartItem;
import it.dpelliccioli.exam.sales.taxes.parser.ISalesTaxParser;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class CartParserTest {

	@Autowired
	private ISalesTaxParser cartParser;

	@Test
	public void testNullInput() {
		assertTrue(cartParser.parseSales(null).isEmpty());
	}

	@Test
	public void testEmptyInput() {
		assertTrue(cartParser.parseSales(new ArrayList<String>(0)).isEmpty());
	}

	@Test
	public void testNotEmptyInput() {
		List<String> input1 = Arrays
				.asList(new String[] { "1 book at 12.49", "1 music CD at 14.99", "1 imported chocolate at 0.85" });
		List<CartItem> parsedInput = cartParser.parseSales(input1);
		assertNotNull(parsedInput);
		assertFalse(parsedInput.isEmpty());
		assertEquals(3, parsedInput.size());
		parsedInput.forEach(x -> {
			assertNotNull(x);
			assertNotNull(x.getItem());
			assertTrue(x.getQuantity() != NumberUtils.INTEGER_ZERO);
			assertTrue(StringUtils.isNotBlank(x.getItem().getDescription()));
			if ("chocolate".equalsIgnoreCase(x.getItem().getDescription())) {
				assertTrue(x.getItem().isImported());
			} else {
				assertFalse(x.getItem().isImported());
			}
		});
	}

	@Test
	public void testMisplacedInput1() {
		List<String> input1 = Arrays
				.asList(new String[] { "1 book 12.49", "1 music CD at 14.99", "1 chocolate at 0.85" });
		List<CartItem> parsedInput = cartParser.parseSales(input1);
		assertNotNull(parsedInput);
		assertFalse(parsedInput.isEmpty());
		assertEquals(2, parsedInput.size());
		parsedInput.forEach(x -> {
			assertNotNull(x);
			assertNotNull(x.getItem());
			assertTrue(x.getQuantity() != NumberUtils.INTEGER_ZERO);
			assertTrue(StringUtils.isNotBlank(x.getItem().getDescription()));
			assertFalse(x.getItem().isImported());
		});
	}

}
