package br.com.processfile.service.process;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processfile.service.model.Arquivo;
import br.com.processfile.service.model.SummaryImport;
import br.com.processfile.service.process.exported.ProcessExportFile;
import br.com.processfile.service.process.imported.ProcessImportFile;
import br.com.processfile.service.process.summary.ProcessSummaryFile;

@Service
public class ProcessFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessFileService.class);

	@Autowired
	private ProcessImportFile processImportFile;

	@Autowired
	private ProcessSummaryFile summaryImportFile;

	@Autowired
	private ProcessExportFile processExportFile;

	public void process(Path path) {
		LOGGER.info(" >> process");

		try {
			LOGGER.debug("File de leitura: {}", path);

			LOGGER.info("Importando arquivo...");
			Arquivo arquivo = this.processImportFile.importFile(path);

			LOGGER.info("Sumarizando informações...");
			SummaryImport summary = this.summaryImportFile.summaryProcess(arquivo);

			LOGGER.info("Exportando informações...");
			this.processExportFile.exportFile(summary, path);

		} catch (IOException exe) {
			LOGGER.error(exe.getMessage());
			LOGGER.error(Arrays.toString(exe.getStackTrace()));
		}

		LOGGER.info(" << process");

	}

}
