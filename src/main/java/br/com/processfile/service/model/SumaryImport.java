package br.com.processfile.service.model;

import java.io.Serializable;

public class SumaryImport implements Serializable {

	private static final long serialVersionUID = -7378366951271545856L;

	private String nomeArquivo;

	private int quantidadeCliente;

	private int quantidadeVendedor;

	private Long maiorVenda;

	private String piorVendedor;

	public SumaryImport() {
		super();
	}

	public boolean isEmpty() {
		if (this.maiorVenda == 0 && this.piorVendedor.isEmpty() && this.quantidadeCliente == 0
				&& this.quantidadeVendedor == 0) {
			return true;
		} else {
			return false;
		}

	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public int getQuantidadeCliente() {
		return quantidadeCliente;
	}

	public void setQuantidadeCliente(int quantidadeCliente) {
		this.quantidadeCliente = quantidadeCliente;
	}

	public int getQuantidadeVendedor() {
		return quantidadeVendedor;
	}

	public void setQuantidadeVendedor(int quantidadeVendedor) {
		this.quantidadeVendedor = quantidadeVendedor;
	}

	public Long getMaiorVenda() {
		return maiorVenda;
	}

	public void setMaiorVenda(Long maiorVenda) {
		this.maiorVenda = maiorVenda;
	}

	public String getPiorVendedor() {
		return piorVendedor;
	}

	public void setPiorVendedor(String piorVendedor) {
		this.piorVendedor = piorVendedor;
	}

}
