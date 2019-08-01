package br.com.processfile.service.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemVenda extends AbstractArquivo implements Serializable {
	private static final long serialVersionUID = -1931995529236801069L;

	private Long itemId;

	private BigDecimal quantidade;

	private BigDecimal preco;

	public ItemVenda addItemVenda(String itemLine) {
		String[] splitItemLine = itemLine.split(REGEX_TRACO);

		try {
			this.itemId = splitItemLine[0] != null ? new Long(splitItemLine[0].trim()) : null;
			this.quantidade = splitItemLine[1] != null ? new BigDecimal(splitItemLine[1].trim()) : BigDecimal.ZERO;
			this.preco = splitItemLine[2] != null ? new BigDecimal(splitItemLine[2].trim()) : BigDecimal.ZERO;

		} catch (Exception e) {
			return null;
		}

		return this;
	}

	public ItemVenda() {
		super();
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		StringBuilder venda = new StringBuilder("Item venda: ");
		venda.append(itemId);
		venda.append(" quantidade: ");
		venda.append(quantidade);
		venda.append(" preco: ");
		venda.append(preco);

		return venda.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
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
		ItemVenda other = (ItemVenda) obj;
		if (itemId == null) {
			if (other.itemId != null) {
				return false;
			}
		} else if (!itemId.equals(other.itemId)) {
			return false;
		}
		return true;
	}

}