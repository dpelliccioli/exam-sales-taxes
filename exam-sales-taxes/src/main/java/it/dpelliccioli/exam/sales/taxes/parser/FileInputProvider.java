/**
 * 
 */
package it.dpelliccioli.exam.sales.taxes.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Provide an input reader from File
 * 
 * @author dpelliccioli
 *
 */
@Slf4j
@Component
public class FileInputProvider implements InputProvider {

	@Value("${input.file.path:classpath:input/input1}")
	private String defaultFilepath;

	/**
	 * {@inheritDoc}
	 * @see it.dpelliccioli.exam.sales.taxes.parser.InputProvider#createInput(java.lang.
	 *      String[])
	 */
	@Override
	public List<String> createInput(String[] args) {
		List<String> input = new ArrayList<>();

		String path = defaultFilepath;
		if (args != null && args.length > NumberUtils.INTEGER_ZERO) {
			path = args[NumberUtils.INTEGER_ZERO];
		}
		try {
			input = Files.newBufferedReader(ResourceUtils.getFile(path).toPath()).lines()
					.collect(Collectors.toList());
		} catch (IOException e) {
			if(log.isErrorEnabled()) {
				log.error("Error during file("+path+") loading", e);
			}
		}

		return input;
	}

}
