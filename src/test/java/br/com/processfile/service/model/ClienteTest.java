package br.com.processfile.service.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteTest {

	@Test
	public void testCliente() {
		Cliente cliente = new Cliente();
		cliente.setCnpj("2345675434544345");
		cliente.setNome("Jose");
		cliente.setRamoAtividade("Rural");
		cliente.setTipo("002");

		assertNotNull(cliente.getNome());
		assertNotNull(cliente.getCnpj());
		assertNotNull(cliente.getRamoAtividade());
		assertNotNull(cliente.getTipo());

	}

}
