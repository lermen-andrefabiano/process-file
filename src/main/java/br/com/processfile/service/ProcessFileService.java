package br.com.processfile.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processfile.filter.FiltroArquivoDat;

@Service
public class ProcessFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ListenerProcessFileService.class);

	@Autowired
	private ImportFileService importFileService;

	public void process(String path) {
		LOGGER.info("Verificando se há arquivos de entrada: {}", path);

		File diretorio = new File(path);
		File arquivos[] = diretorio.listFiles(new FiltroArquivoDat());

		for (File file : arquivos) {
			LOGGER.info(file.getName());
			this.importFileService.process(file.getName());
		}

	}

}
