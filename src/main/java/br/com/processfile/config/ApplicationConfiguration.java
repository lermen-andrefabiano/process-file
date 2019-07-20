package br.com.processfile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Value("${app.process.file.path.in:\\data\\in}")
	private String pathIn;

	@Value("${app.process.file.path.out:\\data\\out}")
	private String pathOut;

	@Value("${app.process.file.suffix.in:.dat}")
	private String suffixFilter;

	@Value("${app.process.file.suffix.out:.done}")
	private String suffixProcess;

	@Value("${app.enviroment.home.path}")
	private String homePath;

	@Value("${app.label.qdtCliente}")
	private String lbQdtCliente;

	@Value("${app.label.qtdVendedor}")
	private String lbQtdVendedor;

	@Value("${app.label.maiorVenda}")
	private String lbMaiorVenda;

	@Value("${app.label.piorVendedor}")
	private String lbPiorVendedor;

	public String getPathIn() {
		return pathIn;
	}

	public String getPathOut() {
		return pathOut;
	}

	public String getSuffixFilter() {
		return suffixFilter;
	}

	public String getSuffixProcess() {
		return suffixProcess;
	}

	public String getHomePath() {
		return homePath;
	}

	public String getLbMaiorVenda() {
		return lbMaiorVenda;
	}

	public String getLbPiorVendedor() {
		return lbPiorVendedor;
	}

	public String getLbQdtCliente() {
		return lbQdtCliente;
	}

	public String getLbQtdVendedor() {
		return lbQtdVendedor;
	}

}