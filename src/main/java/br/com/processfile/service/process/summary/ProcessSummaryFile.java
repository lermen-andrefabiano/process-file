package br.com.processfile.service.process.summary;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.processfile.service.model.Arquivo;
import br.com.processfile.service.model.SummaryImport;
import br.com.processfile.service.model.Venda;
import br.com.processfile.service.process.comparator.ComparatorMaiorVenda;
import br.com.processfile.service.process.comparator.ComparatorPiorVendedor;

@Component
public class ProcessSummaryFile {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessSummaryFile.class);

	public SummaryImport summaryProcess(Arquivo arquivo) {
		LOGGER.info(" >> sumaryProcess");

		SummaryImport summary = new SummaryImport();
		summary.setPiorVendedor(this.obterPiorVendedor(arquivo));
		summary.setMaiorVenda(this.obterMaiorVenda(arquivo));
		summary.setQuantidadeCliente(this.obterQuantidadeClientes(arquivo));
		summary.setQuantidadeVendedor(this.obterQuantidadeVendedores(arquivo));

		LOGGER.info(" << sumaryProcess");

		return summary;

	}

	/**
	 * Recupera a quantidade de clientes
	 * 
	 * @return int com o valor total de clientes
	 */
	private int obterQuantidadeClientes(Arquivo arquivo) {
		LOGGER.info(" >> obterQuantidadeClientes");
		return arquivo.getClientes().size();
	}

	/**
	 * Recupera a quantidade de vendedores
	 * 
	 * @return int com o valor total de vendedores
	 */
	private int obterQuantidadeVendedores(Arquivo arquivo) {
		LOGGER.info(" >> obterQuantidadeVendedores");
		return arquivo.getVendedores().size();
	}

	/**
	 * Recupera o pior vendedor
	 * 
	 * @return String nome do vendedor
	 */
	private String obterPiorVendedor(Arquivo arquivo) {
		LOGGER.info(" >> obterPiorVendedor");
		Map<String, BigDecimal> vendasVendedor = this.summaryVenda(arquivo);

		Entry<String, BigDecimal> piorVendedor = null;
		if (!vendasVendedor.isEmpty()) {
			piorVendedor = Collections.min(vendasVendedor.entrySet(), new ComparatorPiorVendedor());
		}

		LOGGER.info(" << obterPiorVendedor");
		return piorVendedor != null ? piorVendedor.getKey() : "";
	}

	private Map<String, BigDecimal> summaryVenda(Arquivo arquivo) {
		LOGGER.info(" >> summaryVenda");

		Map<String, BigDecimal> vendasVendedor = arquivo.getVendas().stream()
				.collect(Collectors.toMap(venda -> venda.getVendedor(), venda -> venda.getTotal(),
						(oldValue, newValue) -> oldValue.add(newValue)));

		LOGGER.info(" << summaryVenda");
		return vendasVendedor;
	}

	/**
	 * Recupera a maior venda
	 * 
	 * @return id da maio venda
	 */
	private Long obterMaiorVenda(Arquivo arquivo) {
		LOGGER.info(" >> obterMaiorVenda");

		Venda maiorVenda = null;

		if (!arquivo.getVendas().isEmpty()) {
			maiorVenda = Collections.max(arquivo.getVendas(), new ComparatorMaiorVenda());
		}

		LOGGER.info(" << obterMaiorVenda");
		return maiorVenda != null ? maiorVenda.getId() : 0l;
	}

}
