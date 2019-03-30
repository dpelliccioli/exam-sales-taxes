package it.dpelliccioli.exam.sales.taxes.examsalestaxes;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import it.dpelliccioli.exam.sales.taxes.examsalestaxes.config.TestConfig;
import it.dpelliccioli.exam.sales.taxes.parser.FileInputProvider;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class InputProviderTest {
	
	@Autowired
	private FileInputProvider inputProvider;
	
	@Test
	public void testNullInput() {
		assertFalse(inputProvider.createInput(null).isEmpty());
	}
	
	@Test
	public void testEmptyInput() {
		//it takes the  default configuration
		assertFalse(inputProvider.createInput(new String[] {}).isEmpty());
	}
	
}
