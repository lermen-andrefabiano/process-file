package br.com.processfile.service.model;

import java.io.Serializable;

/**
 * 
 * Classe modelo para representar os clientes
 * 
 * @author andre.lermen
 *
 */
public class Cliente extends AbstractArquivo implements Serializable {

	private static final long serialVersionUID = 8311251016940923017L;

	private String tipo;

	private String cnpj;

	private String nome;

	private String ramoAtividade;

	public Cliente addCliente(String line) {
		String[] splitLine = line.split(REGEX_C);

		this.tipo = splitLine[0];
		this.cnpj = splitLine[1];
		this.nome = splitLine[2];
		this.ramoAtividade = splitLine[3];

		return this;

	}

	public Cliente() {
		super();
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	@Override
	public String toString() {
		StringBuilder cliente = new StringBuilder("cnpj: ");
		cliente.append(cnpj);
		cliente.append(" nome: ");
		cliente.append(nome);
		cliente.append(" ramo de atividade: ");
		cliente.append(ramoAtividade);

		return cliente.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cliente other = (Cliente) obj;
		if (cnpj == null) {
			if (other.cnpj != null) {
				return false;
			}
		} else if (!cnpj.equals(other.cnpj)) {
			return false;
		}
		return true;
	}

}
