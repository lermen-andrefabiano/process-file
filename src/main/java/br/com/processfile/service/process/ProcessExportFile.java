package br.com.processfile.service.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.processfile.config.ApplicationConfiguration;
import br.com.processfile.service.model.SumaryImport;
import br.com.processfile.util.Util;

@Component
public class ProcessExportFile {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessImportFile.class);

	@Autowired
	private ApplicationConfiguration config;

	public void exportFile(SumaryImport sumary) throws IOException {
		LOGGER.info(" >> exportFile");

		String fileExport = this.obterSumarizacao(sumary);

		LOGGER.debug("Arquivo de saida {}", fileExport);

		String nameFile = sumary.getNomeArquivo().replace(this.config.getSuffixFilter(),
				this.config.getSuffixProcess());

		LOGGER.debug("Arquivo de saida nome {}", nameFile);

		String pathOut = this.config.getHomePath().concat(this.config.getPathOut());

		LOGGER.debug("Arquivo de saida path out {}", pathOut);

		Util.isDiretorioExists(pathOut);

		Files.write(Paths.get(pathOut.concat(nameFile)), this.obterSumarizacao(sumary).getBytes());

		LOGGER.info(" << exportFile");

	}

	private String obterSumarizacao(SumaryImport sumary) {
		LOGGER.info(" >> Criando sumarizacao");
		
		if(sumary.isEmpty()) {
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
