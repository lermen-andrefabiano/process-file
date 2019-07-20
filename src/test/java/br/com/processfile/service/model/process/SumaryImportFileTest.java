package br.com.processfile.service.model.process;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.processfile.service.model.Arquivo;
import br.com.processfile.service.model.SumaryImport;
import br.com.processfile.service.process.SumaryImportFile;

@SpringBootTest
public class SumaryImportFileTest {

	private SumaryImportFile sumaryImportFile;

	@Before
	public void init() {
		this.sumaryImportFile = new SumaryImportFile();
	}

	@Test
	public void sumaryQuantidadeCliente() {

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Teste unitário");

		arquivo.addLine("002ç2345675434544345çJose da SilvaçRural");

		SumaryImport sumary = this.sumaryImportFile.sumaryProcess(arquivo);

		Assert.assertEquals(1, sumary.getQuantidadeCliente());
	}

	@Test
	public void sumaryQuantidadeVendedor() {

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Teste unitário");

		arquivo.addLine("001ç1234567891234çPedroç50000");

		SumaryImport sumary = this.sumaryImportFile.sumaryProcess(arquivo);

		Assert.assertEquals(1, sumary.getQuantidadeVendedor());
	}

	@Test
	public void sumaryMaiorVenda() {

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Teste unitário");

		arquivo.addLine("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");

		SumaryImport sumary = this.sumaryImportFile.sumaryProcess(arquivo);

		Assert.assertEquals(8, sumary.getMaiorVenda().longValue());
	}

	@Test
	public void sumaryIsEmpty() {

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Teste unitário");

		SumaryImport sumary = this.sumaryImportFile.sumaryProcess(arquivo);

		Assert.assertTrue(sumary.isEmpty());
	}

}
