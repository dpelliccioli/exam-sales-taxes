/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import it.dpelliccioli.exam.sales.taxes.model.CartItem;
import it.dpelliccioli.exam.sales.taxes.model.Product;

/**
 * Parse a list of string in the specified format '[quantity] [imported(optional)] [description] at [price]'
 * into a CartItem object of the domain model
 * 
 * Example of parsed string: '1 book at 12.49'
 * 
 * @author dpelliccioli
 *
 */
@Component
public class CartParser implements ISalesTaxParser {

	private static final String ITEM_REGEX = "(\\d+\\s*)(imported\\s*)?([\\w\\s*]+)(at\\s*)(\\d+\\.\\d+)";
	private static final Integer GROUP_QNT = 1;
	private static final Integer GROUP_IMP = 2;
	private static final Integer GROUP_DESC = 3;
	private static final Integer GROUP_AMOUNT = 5;

	/**
	 * {@inheritDoc}
	 * @see it.dpelliccioli.exam.sales.taxes.parser.ISalesTaxParser#parseSales(java.util.List)
	 */
	@Override
	public List<CartItem> parseSales(List<String> input) {

		List<CartItem> items = new ArrayList<>();

		if (input != null && !input.isEmpty()) {

			Pattern inputPattern = Pattern.compile(ITEM_REGEX, Pattern.CASE_INSENSITIVE);

			input.forEach(str -> {
				if (!StringUtils.isEmpty(str)) {
					Matcher inputMatcher = inputPattern.matcher(str);
					if (inputMatcher.find()) {

						CartItem item = CartItem.builder()
								.quantity(Integer.parseInt(StringUtils.trimWhitespace(inputMatcher.group(GROUP_QNT))))
								.item(Product.builder()
										.description(StringUtils.trimWhitespace(inputMatcher.group(GROUP_DESC)))
										.imported(!StringUtils.isEmpty(inputMatcher.group(GROUP_IMP)))
										.price(new BigDecimal(StringUtils.trimWhitespace(inputMatcher.group(GROUP_AMOUNT))))
										.build())
								.build();

						items.add(item);
					}
				}
			});
		}

		return items;
	}

}
