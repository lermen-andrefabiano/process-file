package br.com.processfile.service.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.processfile.service.model.Arquivo;

@Component
public class ProcessImportFile {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessImportFile.class);

	public Arquivo importFile(String file) throws IOException {
		LOGGER.info(" >> importFile");

		Arquivo arquivo = new Arquivo();

		Path path = Paths.get(file);
		
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