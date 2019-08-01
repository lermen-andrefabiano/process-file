package br.com.processfile.service.model;

import java.io.Serializable;

public class SummaryImport implements Serializable {

	private static final long serialVersionUID = -7378366951271545856L;

	private int quantidadeCliente;

	private int quantidadeVendedor;

	private Long maiorVenda;

	private String piorVendedor;

	public SummaryImport() {
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
