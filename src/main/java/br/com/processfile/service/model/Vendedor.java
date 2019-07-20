package br.com.processfile.service.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * Classe modelo para representar os vendedores
 * 
 * @author andre.lermen
 *
 */
public class Vendedor extends AbstractArquivo implements Serializable {

	private static final long serialVersionUID = -1870050620956034343L;

	private String tipo;

	private String cpf;

	private String nome;

	private BigDecimal salario;

	public Vendedor addVendedor(String line) {
		String[] splitLine = line.split(REGEX_C);

		this.tipo = splitLine[0];
		this.cpf = splitLine[1];
		this.nome = splitLine[2];
		this.salario = splitLine[3] != null ? new BigDecimal(splitLine[3]) : BigDecimal.ZERO;

		return this;

	}

	public Vendedor() {
		super();
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		StringBuilder vendedor = new StringBuilder("cpf: ");
		vendedor.append(cpf);
		vendedor.append(" nome: ");
		vendedor.append(nome);
		vendedor.append(" salário: ");
		vendedor.append(salario);

		return vendedor.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Vendedor other = (Vendedor) obj;
		if (cpf == null) {
			if (other.cpf != null) {
				return false;
			}
		} else if (!cpf.equals(other.cpf)) {
			return false;
		}
		return true;
	}

}
