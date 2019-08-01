package br.com.processfile.service.process.exported;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.processfile.config.ApplicationConfiguration;
import br.com.processfile.service.model.SummaryImport;
import br.com.processfile.util.Util;

@Component
public class ProcessExportFile {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessExportFile.class);

	@Autowired
	private ApplicationConfiguration config;

	public void exportFile(SummaryImport sumary, Path path) throws IOException {
		LOGGER.info(" >> exportFile");

		String pathOut = this.config.getHomePath().concat(this.config.getPathOut());

		Util.isDiretorioExists(pathOut);

		LOGGER.debug("Path arquivo de saida: {}", pathOut);

		String fileExport = this.obterSummarizacao(sumary);

		LOGGER.debug("Arquivo de saida: {}", fileExport);

		String nameFileIn = path.toFile().getName();

		String nameFileOut = nameFileIn.replace(this.config.getSuffixFilter(), this.config.getSuffixProcess());

		LOGGER.debug("Nome arquivo de saida: {}", nameFileOut);

		Files.write(Paths.get(pathOut.concat(this.addDateTime(nameFileOut))), fileExport.getBytes());

		// Files.move(path, pathOut.concat(this.addDateTime(nameFileIn)));

		LOGGER.info(" << exportFile");

	}

	private String addDateTime(String nameFile) {
		LocalDateTime hoje = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmssSSS-");

		return hoje.format(formatter).concat(nameFile);
	}

	private String obterSummarizacao(SummaryImport sumary) {
		LOGGER.info(" >> Criando sumarizacao");

		if (sumary.isEmpty()) {
			LOGGER.warn(this.config.getLbArquivoVazio());
			return this.config.getLbArquivoVazio();
		}

		StringBuilder saida = new StringBuilder(this.config.getLbQdtCliente());
		saida.append(sumary.getQuantidadeCliente());
		saida.append("\n");
		saida.append(this.config.getLbQtdVendedor());
		saida.append(sumary.getQuantidadeVendedor());
		saida.append("\n");
		saida.append(this.config.getLbMaiorVenda());
		saida.append(sumary.getMaiorVenda());
		saida.append("\n");
		saida.append(this.config.getLbPiorVendedor());
		saida.append(sumary.getPiorVendedor());

		LOGGER.info(" << Criando sumarizacao");

		return saida.toString();
	}

	public void setConfig(ApplicationConfiguration config) {
		this.config = config;
	}
}
