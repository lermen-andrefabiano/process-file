package br.com.processfile.service.model;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VendedorTest {

	@Test
	public void testVendedor() {
		Vendedor vendedor = new Vendedor();
		vendedor.setCpf("1234567891234");
		vendedor.setSalario(new BigDecimal("10000"));
		vendedor.setTipo("002");
		vendedor.setNome("Jose");

		assertNotNull(vendedor.getNome());
		assertNotNull(vendedor.getCpf());
		assertNotNull(vendedor.getSalario());
		assertNotNull(vendedor.getTipo());

	}

}
