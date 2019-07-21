package br.com.processfile.service.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VendaTest {

	@Test
	public void testVenda() {
		ItemVenda item = new ItemVenda();
		item.setItemId(10l);
		item.setPreco(new BigDecimal("10"));
		item.setQuantidade(new BigDecimal("10"));

		Venda venda = new Venda();
		venda.setId(1l);
		venda.setTipo("003");
		venda.setVendedor("Pedro");
		venda.getVendaItens().add(item);
		venda.getValorTotal();

		assertNotNull(venda.getId());
		assertNotNull(venda.getTipo());
		assertNotNull(venda.getTotal());
		assertNotNull(venda.getVendedor());
		assertEquals(1, venda.getVendaItens().size());

	}

}
