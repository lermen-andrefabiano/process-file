package br.com.processfile.service.model.process;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.processfile.service.model.Arquivo;
import br.com.processfile.service.model.Cliente;
import br.com.processfile.service.model.ItemVenda;
import br.com.processfile.service.model.SumaryImport;
import br.com.processfile.service.model.Venda;
import br.com.processfile.service.model.Vendedor;
import br.com.processfile.service.process.ProcessExportFile;
import br.com.processfile.service.process.ProcessImportFile;
import br.com.processfile.service.process.SumaryImportFile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessImportFileTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessImportFileTest.class);

	@Autowired
	private ProcessImportFile processImportFile;

	@Autowired
	private SumaryImportFile sumaryImportFile;

	@Autowired
	private ProcessExportFile processExportFile;

	private String file;

	@Before
	public void init() {
		this.file = "/home/andre/git/file/data/in/process1.dat";
	}

	@Test
	public void importFile() throws IOException {

		Path path = Paths.get(file);

		Arquivo arquivo = this.processImportFile.importFile(file);

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
