package br.com.processfile.service.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe modelo para representar as vendas
 * 
 * @author andre.lermen
 *
 */
public class Venda extends AbstractArquivo implements Serializable {

	private static final long serialVersionUID = 5862716229111497751L;

	private String tipo;

	private Long id;

	private String vendedor;

	private BigDecimal total;

	private List<ItemVenda> vendaItens;
	
	public Venda addVenda(String line) {
		String[] splitLine = line.split(REGEX_C);

		this.tipo = splitLine[0];
		this.id = splitLine[1] != null ? new Long(splitLine[1].trim()) : null;
		this.trataItemVenda(splitLine[2]);
		this.vendedor = splitLine[3];
		
		this.getValorTotal();

		return this;

	}

	private void trataItemVenda(String line) {
		line = line.replace("[", "");
		line = line.replace("]", "");

		String[] splitLine = line.split(REGEX_VIRGULA);

		for (String itemLine : splitLine) {
			this.vendaItens.add(new ItemVenda().addItemVenda(itemLine));
		}
	}

	/**
	 * Método que calcula o valor total da venda multiplicando a quantidade * preço
	 * 
	 * @return BigDecimal valor total da venda
	 */
	public void getValorTotal() {
		for (ItemVenda item : this.vendaItens) {
			this.total = this.total.add(item.getQuantidade().multiply(item.getPreco()));
		}

	}

	public Venda() {
		super();
		this.vendaItens = new ArrayList<>();
		this.total = BigDecimal.ZERO;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public List<ItemVenda> getVendaItens() {
		return vendaItens;
	}

	public void setVendaItens(List<ItemVenda> vendaItens) {
		this.vendaItens = vendaItens;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotal() {
		return total;
	}

	@Override
	public String toString() {
		StringBuilder venda = new StringBuilder("venda: ");
		venda.append(id);
		venda.append(" vendedor: ");
		venda.append(vendedor);
		venda.append(" total venda: ");
		venda.append(total);

		return venda.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
