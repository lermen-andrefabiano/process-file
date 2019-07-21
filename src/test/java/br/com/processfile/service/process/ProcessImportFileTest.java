package br.com.processfile.service.process;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

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
import br.com.processfile.service.model.SumaryImport;
import br.com.processfile.service.model.Venda;
import br.com.processfile.service.model.Vendedor;

@SpringBootTest
public class ProcessImportFileTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessImportFileTest.class);

	private static final String USER_DIR = "user.dir";

	private static final String DATA_IN_PROCESS_DAT = "/src/test/resources/data/in/process1.dat";

	private ProcessImportFile processImportFile;

	private SumaryImportFile sumaryImportFile;

	private ProcessExportFile processExportFile;

	@MockBean
	private ApplicationConfiguration config;

	private String path;

	@Before
	public void init() throws URISyntaxException {
		this.processImportFile = new ProcessImportFile();
		this.sumaryImportFile = new SumaryImportFile();
		this.processExportFile = new ProcessExportFile();

		this.path = System.getProperty(USER_DIR) + DATA_IN_PROCESS_DAT;

	}

	@Test
	public void importFilePath() throws IOException {

		Arquivo arquivo = this.processImportFile.importFile(path);

		LOGGER.info(arquivo.getNome());

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

		SumaryImport sumary = this.sumaryImportFile.sumaryProcess(arquivo);

		assertEquals(2, sumary.getQuantidadeCliente());

	}

	@Test
	public void importFile() throws IOException {

		Arquivo arquivo = this.processImportFile.importFile(path);

		LOGGER.info(arquivo.getNome());

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

		SumaryImport sumary = this.sumaryImportFile.sumaryProcess(arquivo);

		this.processExportFile.exportFile(sumary);

	}

}