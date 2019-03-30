package it.dpelliccioli.exam.sales.taxes.examsalestaxes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import it.dpelliccioli.exam.sales.taxes.examsalestaxes.config.TestConfig;
import it.dpelliccioli.exam.sales.taxes.model.CartItem;
import it.dpelliccioli.exam.sales.taxes.model.Product;
import it.dpelliccioli.exam.sales.taxes.model.Receipt;
import it.dpelliccioli.exam.sales.taxes.service.IReceiptService;
import it.dpelliccioli.exam.sales.taxes.service.ITaxCalculator;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ReceiptServiceTest {

	@Autowired
	private ITaxCalculator taxCalculator;

	@Value("${product.sales.tax:0.10}")
	private BigDecimal salesTax;

	@Value("${product.import.tax:0.05}")
	private BigDecimal importTax;

	@Autowired
	private IReceiptService receiptService;
	
	@Before
	public void setUp() {
		//set tax calculator property
		ReflectionTestUtils.setField(taxCalculator, "exclusions", Arrays.asList(new String[] { "chocolate" }));
		ReflectionTestUtils.setField(taxCalculator, "salesTax", salesTax);
		ReflectionTestUtils.setField(taxCalculator, "importTax", importTax);
		//set tax calculator
		ReflectionTestUtils.setField(receiptService, "taxCalculator", taxCalculator);
	}

	@Test
	public void testNullCart() {
		assertNull(receiptService.computeReceipt(null));
	}

	@Test
	public void testEmptyCart() {
		// it takes the default configuration
		assertNull(receiptService.computeReceipt(new ArrayList<CartItem>(0)));
	}

	@Test
	public void testFullCart() {
		Product prod1 = Product.builder().price(new BigDecimal("10")).imported(true).description("cheesecacke").build();
		Product prod2 = Product.builder().price(new BigDecimal("20")).imported(false).description("chocolate").build();
		List<CartItem> items = Arrays.asList(new CartItem[] { CartItem.builder().quantity(1).item(prod1).build(),
				CartItem.builder().quantity(1).item(prod2).build() });
		Receipt receipt = receiptService.computeReceipt(items);
		assertNotNull(receipt);
		assertNotNull(receipt.getItems());
		assertNotNull(receipt.getAmount());
		assertFalse(receipt.getItems().isEmpty());
		assertEquals(2, receipt.getItems().size());
		receipt.getItems().forEach(x -> {
			assertTrue(x.getQuantity() != NumberUtils.INTEGER_ZERO);
			assertTrue(StringUtils.isNotBlank(x.getDescription()));
			assertFalse(BigDecimal.ZERO.equals(x.getAmount()));
		});
		
	}

}
