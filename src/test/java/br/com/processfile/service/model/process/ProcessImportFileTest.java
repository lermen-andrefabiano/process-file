package br.com.processfile.service.model.process;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

//@RunWith(SpringRunner.class)
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

	//@Test
	public void importFilePath() throws IOException {

		List<String> lineList = new ArrayList<>();
		lineList.add("001ç1234567891234çPedroç50000");
		lineList.add("001ç3245678865434çPauloç40000.99");
		lineList.add("002ç2345675434544345çJose da SilvaçRural");
		lineList.add("002ç2345675434544345çJose da SilvaçRural");
		lineList.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
		lineList.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");

		when(Files.readAllLines(Mockito.any(Path.class))).thenReturn(lineList);
		
		Arquivo arquivo = this.processImportFile.importFile("");
		
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

	//@Test
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
