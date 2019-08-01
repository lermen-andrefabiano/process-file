package br.com.processfile.service.start;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import br.com.processfile.config.ApplicationConfiguration;
import br.com.processfile.service.process.ProcessFileService;

@Order(value = 1)
@Service
public class StartFileService implements CommandLineRunner {

	private static final String DAT = ".dat";

	private static final Logger LOGGER = LoggerFactory.getLogger(StartFileService.class);

	@Autowired
	private ProcessFileService processFileService;

	@Autowired
	private ApplicationConfiguration config;

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Run...");
		//this.process();
	}

	public void process() {
		LOGGER.info(">> process");

		String path = this.config.getHomePath().concat(this.config.getPathIn());

		LOGGER.info("Verificando se há arquivos de entrada: {}", path);

		try {
			Stream<Path> files = Files.list(Paths.get(path)).filter(file -> file.toString().endsWith(DAT));

			files.forEach(file -> this.processFileService.process(file));

		} catch (IOException exe) {
			LOGGER.error(exe.getMessage());
			LOGGER.error(Arrays.toString(exe.getStackTrace()));
		}

		LOGGER.info("<< process");

	}

}
