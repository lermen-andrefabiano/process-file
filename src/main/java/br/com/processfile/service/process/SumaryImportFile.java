package br.com.processfile.service.process;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.processfile.service.model.Arquivo;
import br.com.processfile.service.model.SumaryImport;
import br.com.processfile.service.model.Venda;
import br.com.processfile.service.process.comparator.ComparatorMaiorVenda;
import br.com.processfile.service.process.comparator.ComparatorPiorVendedor;

@Component
public class SumaryImportFile {

	private static final Logger LOGGER = LoggerFactory.getLogger(SumaryImportFile.class);

	public SumaryImport sumaryProcess(Arquivo arquivo) {
		LOGGER.info(" >> sumaryProcess");

		SumaryImport sumaryImport = new SumaryImport();
		sumaryImport.setNomeArquivo(arquivo.getNome());
		sumaryImport.setPiorVendedor(this.obterPiorVendedor(arquivo));
		sumaryImport.setMaiorVenda(this.obterMaiorVenda(arquivo));
		sumaryImport.setQuantidadeCliente(this.obterQuantidadeClientes(arquivo));
		sumaryImport.setQuantidadeVendedor(this.obterQuantidadeVendedores(arquivo));

		LOGGER.info(" << sumaryProcess");

		return sumaryImport;

	}

	/**
	 * Recupera a quantidade de clientes
	 * 
	 * @return int com o valor total de clientes
	 */
	private int obterQuantidadeClientes(Arquivo arquivo) {
		return arquivo.getClientes().size();
	}

	/**
	 * Recupera a quantidade de vendedores
	 * 
	 * @return int com o valor total de vendedores
	 */
	private int obterQuantidadeVendedores(Arquivo arquivo) {
		return arquivo.getVendedores().size();
	}

	/**
	 * Recupera o pior vendedor
	 * 
	 * @return String nome do vendedor
	 */
	private String obterPiorVendedor(Arquivo arquivo) {
		Map<String, BigDecimal> vendasVendedor = this.sumaryVenda(arquivo);

		Entry<String, BigDecimal> piorVendedor = null;
		if (!vendasVendedor.isEmpty()) {
			piorVendedor = Collections.min(vendasVendedor.entrySet(), new ComparatorPiorVendedor());
		}

		return piorVendedor != null ? piorVendedor.getKey() : "";
	}

	private Map<String, BigDecimal> sumaryVenda(Arquivo arquivo) {
		Map<String, BigDecimal> vendasVendedor = new HashMap<>();

		for (Venda venda : arquivo.getVendas()) {
			BigDecimal result = vendasVendedor.get(venda.getVendedor());

			if (result != null) {
				result = result.add(venda.getTotal());
			} else {
				result = venda.getTotal();
			}

			vendasVendedor.put(venda.getVendedor(), result);

		}

		return vendasVendedor;
	}

	/**
	 * Recupera a maior venda
	 * 
	 * @return id da maio venda
	 */
	private Long obterMaiorVenda(Arquivo arquivo) {
		Venda maiorVenda = null;
		if (!arquivo.getVendas().isEmpty()) {
			maiorVenda = Collections.max(arquivo.getVendas(), new ComparatorMaiorVenda());
		}

		return maiorVenda != null ? maiorVenda.getId() : 0l;
	}

}
