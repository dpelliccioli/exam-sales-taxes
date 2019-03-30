package it.dpelliccioli.exam.sales.taxes.examsalestaxes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import it.dpelliccioli.exam.sales.taxes.model.CartItem;
import it.dpelliccioli.exam.sales.taxes.model.Receipt;
import it.dpelliccioli.exam.sales.taxes.parser.ISalesTaxParser;
import it.dpelliccioli.exam.sales.taxes.parser.InputProvider;
import it.dpelliccioli.exam.sales.taxes.service.IReceiptService;
import it.dpelliccioli.exam.sales.taxes.writer.IReceiptWriter;
import lombok.extern.slf4j.Slf4j;
/**
 * Class that contains workflow of sales taxes application
 * 
 * @author dpelliccioli
 *
 */
@Slf4j
@SpringBootApplication
@ComponentScan("it.dpelliccioli.*")
public class InitSalesTaxes implements CommandLineRunner{

	
	@Autowired
	InputProvider inputProvider;
	
	@Autowired
	private IReceiptService receiptService;
	
	@Autowired
	private ISalesTaxParser inputParser;
	
	@Autowired
	private IReceiptWriter ouputWriter;

	@Override
	public void run(String... args) throws Exception {
	
		List<String> input = inputProvider.createInput(args);
		
		List<CartItem> itemsList = inputParser.parseSales(input);

		Receipt receipt = receiptService.computeReceipt(itemsList);
		
		log.info(ouputWriter.write(receipt));
		
	}

}
