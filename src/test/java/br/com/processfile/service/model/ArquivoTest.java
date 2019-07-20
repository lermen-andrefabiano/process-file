package br.com.processfile.service.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArquivoTest {

	@Test
	public void arquivoCliente() {
		Arquivo arquivo = new Arquivo();
		arquivo.getClientes().add(new Cliente());

		assertEquals("Testando lista de clientes", 1, arquivo.getClientes().size());

	}

	@Test
	public void arquivoVendedor() {
		Arquivo arquivo = new Arquivo();
		arquivo.getVendedores().add(new Vendedor());

		assertEquals("Testando lista de vendedores", 1, arquivo.getVendedores().size());

	}

	@Test
	public void arquivoVenda() {
		Arquivo arquivo = new Arquivo();
		arquivo.getVendas().add(new Venda());

		assertEquals("Testando lista de vendas", 1, arquivo.getVendas().size());

	}

}
