package br.com.processfile.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import br.com.processfile.config.ApplicationConfiguration;
import br.com.processfile.util.Util;

@Service
public class ListenerProcessFileService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ListenerProcessFileService.class);

	@Autowired
	private ImportFileService importFileService;

	@Autowired
	private ApplicationConfiguration config;

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("listener...");
		this.listenerPath();
	}

	public void listenerPath() {
		LOGGER.info(" >> listenerPath");

		String path = this.config.getHomePath().concat(this.config.getPathIn());

		Util.isDiretorioExists(path);

		this.watchDirectoryPath(path);

		LOGGER.info(" << listenerPath");

	}

	private void watchDirectoryPath(String file) {
		LOGGER.info(" >> watchDirectoryPath");

		LOGGER.debug(" watch directory path file {}", file);

		Path path = Paths.get(file);

		FileSystem fs = path.getFileSystem();

		try (WatchService service = fs.newWatchService()) {

			LOGGER.info("Registrando servico de listener...");

			path.register(service, StandardWatchEventKinds.ENTRY_CREATE);
			path.register(service, StandardWatchEventKinds.ENTRY_MODIFY);

			WatchKey key = null;
			while (true) {
				key = service.take();

				Kind<?> kind = null;
				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					kind = watchEvent.kind();
					if (StandardWatchEventKinds.OVERFLOW == kind) {
						continue;

					} else if (StandardWatchEventKinds.ENTRY_CREATE == kind) {
						Path fileIn = ((WatchEvent<Path>) watchEvent).context();

						LOGGER.info("Novo arquivo encontrado: {}", fileIn);

						if (this.isCorrectFileType(fileIn)) {
							this.importFileService.process(fileIn.toString());
						}

					} else if (StandardWatchEventKinds.ENTRY_MODIFY == kind) {
						Path fileIn = ((WatchEvent<Path>) watchEvent).context();

						LOGGER.info("Arquivo modificado: {}", fileIn);

						if (this.isCorrectFileType(fileIn)) {
							this.importFileService.process(fileIn.toString());
						}

					}
				}

				if (!key.reset()) {
					break;
				}
			}

			LOGGER.info(" << watchDirectoryPath {}", file);

		} catch (IOException exe) {
			LOGGER.error("Erro ao escutar diretorio: ".concat(file));
			LOGGER.error(exe.getMessage());
			LOGGER.error(Arrays.toString(exe.getStackTrace()));
		} catch (InterruptedException exe) {
			LOGGER.error("Erro ao escutar diretorio: ".concat(file));
			LOGGER.error(exe.getMessage());
			LOGGER.error(Arrays.toString(exe.getStackTrace()));
		}

	}

	private boolean isCorrectFileType(Path fileIn) {
		return fileIn.toString().endsWith(this.config.getSuffixFilter());
	}

}
