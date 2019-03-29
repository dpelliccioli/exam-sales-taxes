package it.dpelliccioli.exam.sales.taxes.examsalestaxes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import it.dpelliccioli.exam.sales.taxes.model.CartItem;
import it.dpelliccioli.exam.sales.taxes.model.Receipt;
import it.dpelliccioli.exam.sales.taxes.parser.ISalesTaxParser;
import it.dpelliccioli.exam.sales.taxes.service.ReceiptService;
import it.dpelliccioli.exam.sales.taxes.writer.IReceiptWriter;

@SpringBootApplication
@ComponentScan("it.dpelliccioli.*")
public class InitSalesTaxes implements CommandLineRunner{

	//@Autowired
	//list provider
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private ISalesTaxParser inputParser;
	
	@Autowired
	private IReceiptWriter ouputWriter;

	@Override
	public void run(String... args) throws Exception {
	
		List<String> input = new ArrayList<>(3);
		
		input.add("1 book at 12.49");
		input.add("1 music CD at 14.99");
		input.add("1 chocolate bar at 0.85");
		
		List<CartItem> itemsList = inputParser.parseSales(input);

		Receipt receipt = receiptService.computeReceipt(itemsList);
		
		System.out.println(ouputWriter.write(receipt));
		
	}

}
