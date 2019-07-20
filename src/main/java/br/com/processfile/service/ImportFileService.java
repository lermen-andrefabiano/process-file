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
	private ApplicationConfiguration config;

	@Autowired
	private ProcessImportFile processImportFile;

	@Autowired
	private SumaryImportFile sumaryImportFile;
	
	@Autowired
	private ProcessExportFile processExportFile;

	public void process() {
		LOGGER.info(" >> process");

		try {
			Arquivo arquivo = this.processImportFile.importFile("");
			
			SumaryImport sumary = this.sumaryImportFile.sumaryProcess(arquivo);
			
			this.processExportFile.exportFile(sumary);

		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			LOGGER.error(Arrays.toString(e.getStackTrace()));
		}

		LOGGER.info(" << process");

	}

}
