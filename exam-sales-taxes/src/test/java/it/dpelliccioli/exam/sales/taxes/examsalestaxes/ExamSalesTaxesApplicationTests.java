package it.dpelliccioli.exam.sales.taxes.examsalestaxes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ RoundingTest.class, ReceiptWriterTest.class, ReceiptServiceTest.class, InputProviderTest.class,
		TaxCalculatorTest.class, CartParserTest.class })
public class ExamSalesTaxesApplicationTests {

}
