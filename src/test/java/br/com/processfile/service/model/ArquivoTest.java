package br.com.processfile.service.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArquivoTest {

	@Test
	public void testArquivo() {
		Arquivo arquivo = new Arquivo();
		arquivo.setNome("TESTE UNITARIO");
		arquivo.getClientes().add(new Cliente());
		arquivo.getVendas().add(new Venda());
		arquivo.getVendedores().add(new Vendedor());

		assertNotNull(arquivo.getNome());
		assertNotNull(arquivo.getClientes());
		assertNotNull(arquivo.getVendas());
		assertNotNull(arquivo.getVendedores());

	}

}
