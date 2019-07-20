package br.com.processfile.service.process.comparator;

import java.util.Comparator;

import br.com.processfile.service.model.Venda;

/**
 * 
 * Classe para encontrar a maior venda
 * 
 * @author andre.lermen
 *
 */
public class ComparatorMaiorVenda implements Comparator<Venda> {

	@Override
	public int compare(Venda venda, Venda vendaProx) {
		return venda.getTotal().compareTo(vendaProx.getTotal());
	}

}
