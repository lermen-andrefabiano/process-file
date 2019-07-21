package br.com.processfile.service;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processfile.config.ApplicationConfiguration;
import br.com.processfile.service.model.Arquivo;
import br.com.processfile.service.model.SumaryImport;
import br.com.processfile.service.process.ProcessExportFile;
import br.com.processfile.service.process.ProcessImportFile;
import br.com.processfile.service.process.SumaryImportFile;

@Service
public class ImportFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportFileService.class);

	@Autowired
	private ProcessImportFile processImportFile;

	@Autowired
	private SumaryImportFile sumaryImportFile;

	@Autowired
	private ProcessExportFile processExportFile;

	@Autowired
	private ApplicationConfiguration config;

	public void process(String arquivoIn) {
		LOGGER.info(" >> process");

		try {

			String pathIn = this.config.getHomePath().concat(this.config.getPathIn()).concat(arquivoIn);

			LOGGER.info("Importando arquivo...");
			Arquivo arquivo = this.processImportFile.importFile(pathIn);

			LOGGER.info("Sumarizando informações...");
			SumaryImport sumary = this.sumaryImportFile.sumaryProcess(arquivo);

			LOGGER.info("Exportando informações...");
			this.processExportFile.exportFile(sumary);

		} catch (IOException exe) {
			LOGGER.error(exe.getMessage());
			LOGGER.error(Arrays.toString(exe.getStackTrace()));
		}

		LOGGER.info(" << process");

	}

}
