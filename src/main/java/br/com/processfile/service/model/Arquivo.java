package br.com.processfile.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.processfile.enumerated.FileTipo;

public class Arquivo implements Serializable {

	private static final long serialVersionUID = -7743436195038947329L;
	
	private String nome;

	private List<Cliente> clientes;

	private List<Venda> vendas;

	private List<Vendedor> vendedores;

	public void addLine(String line) {
		if (line.startsWith(FileTipo.UM.getValue())) {
			this.vendedores.add(new Vendedor().addVendedor(line));

		} else if (line.startsWith(FileTipo.DOIS.getValue())) {
			this.clientes.add(new Cliente().addCliente(line));

		} else if (line.startsWith(FileTipo.TRES.getValue())) {
			this.vendas.add(new Venda().addVenda(line));

		}
	}

	public Arquivo() {
		this.clientes = new ArrayList<>();
		this.vendas = new ArrayList<>();
		this.vendedores = new ArrayList<>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

}
