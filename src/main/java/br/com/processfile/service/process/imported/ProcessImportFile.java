package br.com.processfile.service.process.imported;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.processfile.service.model.Arquivo;

@Component
public class ProcessImportFile {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessImportFile.class);

	public Arquivo importFile(Path path) throws IOException {
		LOGGER.info(" >> importFile");

		Arquivo arquivo = new Arquivo();

		List<String> lineList = Files.readAllLines(path);

		lineList.forEach(line -> {
			LOGGER.debug("Linha lida: {}", line);
			arquivo.addLine(line);
		});

		LOGGER.info(" << importFile");

		return arquivo;
	}

}