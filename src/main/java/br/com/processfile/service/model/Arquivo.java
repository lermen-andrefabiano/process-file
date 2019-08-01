package br.com.processfile.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.processfile.enumerated.FileTipo;

public class Arquivo implements Serializable {

	private static final long serialVersionUID = -7743436195038947329L;

	private List<Cliente> clientes;

	private List<Venda> vendas;

	private List<Vendedor> vendedores;

	public void addLine(String line) {
		if (line.startsWith(FileTipo.UM.getValue())) {
			Vendedor vendedor = new Vendedor().addVendedor(line);
			if (vendedor != null) {
				this.vendedores.add(vendedor);
			}

		} else if (line.startsWith(FileTipo.DOIS.getValue())) {
			Cliente cliente = new Cliente().addCliente(line);
			if (cliente != null) {
				this.clientes.add(cliente);
			}

		} else if (line.startsWith(FileTipo.TRES.getValue())) {
			Venda venda = new Venda().addVenda(line);
			if (venda != null) {
				this.vendas.add(venda);
			}

		}
	}

	public Arquivo() {
		this.clientes = new ArrayList<>();
		this.vendas = new ArrayList<>();
		this.vendedores = new ArrayList<>();
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
