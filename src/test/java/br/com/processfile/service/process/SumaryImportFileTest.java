package br.com.processfile.service.process;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.processfile.service.model.Arquivo;
import br.com.processfile.service.model.SummaryImport;
import br.com.processfile.service.process.summary.ProcessSummaryFile;

@SpringBootTest
public class SumaryImportFileTest {

	private ProcessSummaryFile sumaryImportFile;

	@Before
	public void init() {
		this.sumaryImportFile = new ProcessSummaryFile();
	}

	@Test
	public void sumaryQuantidadeCliente() {

		Arquivo arquivo = new Arquivo();

		arquivo.addLine("002ç2345675434544345çJose da SilvaçRural");

		SummaryImport sumary = this.sumaryImportFile.summaryProcess(arquivo);

		Assert.assertEquals(1, sumary.getQuantidadeCliente());
	}

	@Test
	public void sumaryQuantidadeVendedor() {

		Arquivo arquivo = new Arquivo();

		arquivo.addLine("001ç1234567891234çPedroç50000");

		SummaryImport sumary = this.sumaryImportFile.summaryProcess(arquivo);

		Assert.assertEquals(1, sumary.getQuantidadeVendedor());
	}

	@Test
	public void sumaryMaiorVenda() {

		Arquivo arquivo = new Arquivo();

		arquivo.addLine("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");

		SummaryImport sumary = this.sumaryImportFile.summaryProcess(arquivo);

		Assert.assertEquals(8, sumary.getMaiorVenda().longValue());
	}

	@Test
	public void sumaryIsEmpty() {

		Arquivo arquivo = new Arquivo();

		SummaryImport sumary = this.sumaryImportFile.summaryProcess(arquivo);

		Assert.assertTrue(sumary.isEmpty());
	}

}
