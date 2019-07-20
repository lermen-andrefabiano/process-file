package br.com.processfile.service.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.processfile.config.ApplicationConfiguration;
import br.com.processfile.service.model.Arquivo;

@Component
public class ProcessImportFile {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessImportFile.class);
	
	@Autowired
	private ApplicationConfiguration config;

	public Arquivo importFile(String arquivoIn) throws IOException {
		LOGGER.info(" >> importFile");

		Arquivo arquivo = new Arquivo();
		
		String pathIn = this.config.getHomePath().concat(this.config.getPathIn()).concat(arquivoIn);
		
		Path path = Paths.get(pathIn);

		arquivo.setNome(path.toFile().getName());

		List<String> lineList = Files.readAllLines(path);

		for (String line : lineList) {
			LOGGER.debug("Linha: {}", line);

			arquivo.addLine(line);

		}

		LOGGER.info(" << importFile");

		return arquivo;
	}

}