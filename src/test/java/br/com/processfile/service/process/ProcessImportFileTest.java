package br.com.processfile.service.process;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.processfile.config.ApplicationConfiguration;
import br.com.processfile.service.model.Arquivo;
import br.com.processfile.service.model.Cliente;
import br.com.processfile.service.model.ItemVenda;
import br.com.processfile.service.model.SummaryImport;
import br.com.processfile.service.model.Venda;
import br.com.processfile.service.model.Vendedor;
import br.com.processfile.service.process.exported.ProcessExportFile;
import br.com.processfile.service.process.imported.ProcessImportFile;
import br.com.processfile.service.process.summary.ProcessSummaryFile;

@SpringBootTest
public class ProcessImportFileTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessImportFileTest.class);

	private static final String DONE = ".done";

	private static final String DAT = ".dat";

	private static final String USER_DIR = "user.dir";

	private static final String DATA_IN = "\\src\\test\\resources\\data\\in\\process1.dat";

	private static final String DATA_OUT = "\\src\\test\\resources\\data\\out\\";

	private static final String PROCESS_DAT = "process1.dat";

	private ProcessImportFile processImportFile;

	private ProcessSummaryFile sumaryImportFile;

	private ProcessExportFile processExportFile;

	@MockBean
	private ApplicationConfiguration config;

	private Path pathIn;

	private Path pathOut;

	@Before
	public void init() {
		this.processImportFile = new ProcessImportFile();
		this.sumaryImportFile = new ProcessSummaryFile();
		this.processExportFile = new ProcessExportFile();

		this.initConf();

		this.processExportFile.setConfig(this.config);

		this.pathIn = Paths.get(System.getProperty(USER_DIR).concat(DATA_IN));

		this.pathOut = Paths.get(System.getProperty(USER_DIR).concat(DATA_OUT).concat(PROCESS_DAT));

	}

	private void initConf() {
		this.config = new ApplicationConfiguration();

		this.config.setHomePath(System.getProperty(USER_DIR));
		this.config.setPathOut(DATA_OUT);
		this.config.setSuffixFilter(DAT);
		this.config.setSuffixProcess(DONE);
		this.config.setLbMaiorVenda("ID da venda mais cara: ");
		this.config.setLbPiorVendedor("O pior vendedor: ");
		this.config.setLbQdtCliente("Quantidade de clientes no arquivo de entrada: ");
		this.config.setLbQtdVendedor("Quantidade de vendedor no arquivo de entrada: ");
	}

	@Test
	public void importFilePath() throws IOException {

		Arquivo arquivo = this.processImportFile.importFile(this.pathIn);

		for (Cliente cliente : arquivo.getClientes()) {
			LOGGER.info(cliente.toString());
		}

		for (Vendedor vendedor : arquivo.getVendedores()) {
			LOGGER.info(vendedor.toString());
		}

		for (Venda venda : arquivo.getVendas()) {
			LOGGER.info(venda.toString());

			for (ItemVenda item : venda.getVendaItens()) {
				LOGGER.info(item.toString());
			}
		}

		SummaryImport sumary = this.sumaryImportFile.summaryProcess(arquivo);

		assertEquals(2, sumary.getQuantidadeCliente());

	}

	@Test
	public void importFile() throws IOException {

		Arquivo arquivo = this.processImportFile.importFile(this.pathIn);

		for (Cliente cliente : arquivo.getClientes()) {
			LOGGER.info(cliente.toString());
		}

		for (Vendedor vendedor : arquivo.getVendedores()) {
			LOGGER.info(vendedor.toString());
		}

		for (Venda venda : arquivo.getVendas()) {
			LOGGER.info(venda.toString());

			for (ItemVenda item : venda.getVendaItens()) {
				LOGGER.info(item.toString());
			}
		}

		SummaryImport sumary = this.sumaryImportFile.summaryProcess(arquivo);

		this.processExportFile.exportFile(sumary, this.pathOut);

	}

}
