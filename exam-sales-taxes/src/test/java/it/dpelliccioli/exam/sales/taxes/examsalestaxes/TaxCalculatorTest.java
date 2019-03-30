package it.dpelliccioli.exam.sales.taxes.examsalestaxes;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import it.dpelliccioli.exam.sales.taxes.examsalestaxes.config.TestConfig;
import it.dpelliccioli.exam.sales.taxes.model.Product;
import it.dpelliccioli.exam.sales.taxes.service.ITaxCalculator;
import it.dpelliccioli.exam.sales.taxes.util.RoundUtils;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TaxCalculatorTest {

	@Autowired
	private ITaxCalculator taxCalculator;

	@Value("${product.sales.tax:0.10}")
	private BigDecimal salesTax;

	@Value("${product.import.tax:0.05}")
	private BigDecimal importTax;

	@Before
	public void setUp() {
		ReflectionTestUtils.setField(taxCalculator, "exclusions", Arrays.asList(new String[] { "chocolate" }));
		ReflectionTestUtils.setField(taxCalculator, "salesTax", salesTax);
		ReflectionTestUtils.setField(taxCalculator, "importTax", importTax);
	}

	@Test
	public void nullProductTest() {
		assertEquals(BigDecimal.ZERO, taxCalculator.computeTaxes(null));
	}

	@Test
	public void emptyProductTest() {
		assertEquals(BigDecimal.ZERO, taxCalculator.computeTaxes(Product.builder().build()));
	}

	@Test
	public void importedExcludedProductTest() {
		Product prod = Product.builder().price(new BigDecimal("10")).description("chocolate").imported(true).build();
		assertEquals(
				RoundUtils.roundToNearest5(
						prod.getPrice().add(prod.getPrice().multiply(importTax).setScale(2, RoundingMode.HALF_UP))),
				taxCalculator.computeTaxes(prod));
	}

	@Test
	public void notImpotedExcludedProductTest() {
		Product prod = Product.builder().price(new BigDecimal("10")).description("chocolate").imported(false).build();
		assertEquals(RoundUtils.roundToNearest5(prod.getPrice()), taxCalculator.computeTaxes(prod));
	}

	@Test
	public void notExcludedNotImportedProductTest() {
		Product prod = Product.builder().price(new BigDecimal("10")).imported(false).description("cheesecacke").build();
		assertEquals(
				RoundUtils.roundToNearest5(
						prod.getPrice().add(prod.getPrice().multiply(salesTax).setScale(2, RoundingMode.HALF_UP))),
				taxCalculator.computeTaxes(prod));
	}

	@Test
	public void notExcludedImportedProductTest() {
		Product prod = Product.builder().price(new BigDecimal("10")).imported(true).description("cheesecacke").build();
		assertEquals(
				RoundUtils.roundToNearest5(
						prod.getPrice().add(prod.getPrice().multiply(salesTax).setScale(2, RoundingMode.HALF_UP))
								.add(prod.getPrice().multiply(importTax).setScale(2, RoundingMode.HALF_UP))),
				taxCalculator.computeTaxes(prod));
	}
	
}
