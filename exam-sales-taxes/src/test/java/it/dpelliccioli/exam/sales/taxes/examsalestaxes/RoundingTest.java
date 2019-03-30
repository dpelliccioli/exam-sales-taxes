package it.dpelliccioli.exam.sales.taxes.examsalestaxes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import it.dpelliccioli.exam.sales.taxes.util.RoundUtils;

@RunWith(SpringRunner.class)
public class RoundingTest {

	@Test
	public void testNullNumber() {
		assertNull(RoundUtils.roundToFraction(null, RoundUtils.FIVE_CENT));
		assertNull(RoundUtils.roundToNearest5(null));
	}
	
	@Test
	public void testNullFraction() {
		assertEquals(RoundUtils.FIVE_CENT, RoundUtils.roundToFraction(RoundUtils.FIVE_CENT, null));
	}
	
	@Test
	public void testRound5() {
		assertEquals(new BigDecimal("1.00"), RoundUtils.roundToNearest5(new BigDecimal(1.02)));
		assertEquals(new BigDecimal("1.05"), RoundUtils.roundToNearest5(new BigDecimal(1.05)));
		assertEquals(new BigDecimal("1.10"), RoundUtils.roundToNearest5(new BigDecimal(1.08)));
	}
	
}
