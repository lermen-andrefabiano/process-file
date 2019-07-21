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

	@Value("${app.label.arquivo.vazio}")
	private String lbArquivoVazio;

	public String getPathIn() {
		return pathIn;
	}

	public void setPathIn(String pathIn) {
		this.pathIn = pathIn;
	}

	public String getPathOut() {
		return pathOut;
	}

	public void setPathOut(String pathOut) {
		this.pathOut = pathOut;
	}

	public String getSuffixFilter() {
		return suffixFilter;
	}

	public void setSuffixFilter(String suffixFilter) {
		this.suffixFilter = suffixFilter;
	}

	public String getSuffixProcess() {
		return suffixProcess;
	}

	public void setSuffixProcess(String suffixProcess) {
		this.suffixProcess = suffixProcess;
	}

	public String getHomePath() {
		return homePath;
	}

	public void setHomePath(String homePath) {
		this.homePath = homePath;
	}

	public String getLbQdtCliente() {
		return lbQdtCliente;
	}

	public void setLbQdtCliente(String lbQdtCliente) {
		this.lbQdtCliente = lbQdtCliente;
	}

	public String getLbQtdVendedor() {
		return lbQtdVendedor;
	}

	public void setLbQtdVendedor(String lbQtdVendedor) {
		this.lbQtdVendedor = lbQtdVendedor;
	}

	public String getLbMaiorVenda() {
		return lbMaiorVenda;
	}

	public void setLbMaiorVenda(String lbMaiorVenda) {
		this.lbMaiorVenda = lbMaiorVenda;
	}

	public String getLbPiorVendedor() {
		return lbPiorVendedor;
	}

	public void setLbPiorVendedor(String lbPiorVendedor) {
		this.lbPiorVendedor = lbPiorVendedor;
	}

	public String getLbArquivoVazio() {
		return lbArquivoVazio;
	}

	public void setLbArquivoVazio(String lbArquivoVazio) {
		this.lbArquivoVazio = lbArquivoVazio;
	}

}