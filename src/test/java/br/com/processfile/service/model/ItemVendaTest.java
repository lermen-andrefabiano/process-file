package br.com.processfile.service.model;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemVendaTest {

	@Test
	public void testItem() {
		ItemVenda item = new ItemVenda();
		item.setItemId(10l);
		item.setPreco(new BigDecimal("10"));
		item.setQuantidade(new BigDecimal("10"));

		assertNotNull(item.getItemId());
		assertNotNull(item.getPreco());
		assertNotNull(item.getQuantidade());

	}

}
