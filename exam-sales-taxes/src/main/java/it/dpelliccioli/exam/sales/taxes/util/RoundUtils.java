package it.dpelliccioli.exam.sales.taxes.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for rounding number
 * 
 * @author dpelliccioli
 *
 */
public class RoundUtils {

	// avoid instantiation
	private RoundUtils() {
	}

	public static final BigDecimal FIVE_CENT = new BigDecimal(0.05);

	/**
	 * Round input number by the fraction 
	 * 
	 * @param num      - number to round
	 * @param fraction - rounding rule
	 * @return Input number rounded by fraction
	 */
	public static BigDecimal roundToFraction(BigDecimal num, BigDecimal fraction) {

		if (num == null || fraction == null) {
			return num;
		}
		return num.divide(fraction, 0, RoundingMode.UP).multiply(fraction).setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Round input number to the nearest 5 cents
	 * @param num - number to round
	 * @return Input number rounded by 5 cents
	 */
	public static BigDecimal roundToNearest5(BigDecimal num) {
		return roundToFraction(num, FIVE_CENT);
	}

}
