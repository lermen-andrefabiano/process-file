package br.com.processfile.service.listener;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import br.com.processfile.config.ApplicationConfiguration;
import br.com.processfile.service.process.ProcessFileService;
import br.com.processfile.util.Util;

@Order(value = 2)
@Service
public class ListenerProcessFileService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ListenerProcessFileService.class);

	@Autowired
	private ProcessFileService importFileService;

	@Autowired
	private ApplicationConfiguration config;

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Run listener...");
		this.listenerPath();
	}

	public void listenerPath() {
		LOGGER.info(" >> listenerPath");

		String pathIn = this.config.getHomePath().concat(this.config.getPathIn());

		Util.isDiretorioExists(pathIn);

		this.watchDirectory(pathIn);

		LOGGER.info(" << listenerPath");

	}

	public void watchDirectory(String pathIn) {
		LOGGER.info(" >> watchDirectory");

		try {

			WatchService watcher = FileSystems.getDefault().newWatchService();
			Path diretorio = Paths.get(pathIn);
			diretorio.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

			int count = 0;

			while (true) {
				WatchKey key = watcher.take();

				count++;
				LOGGER.debug("Watch Service count: {}", count);

				key.pollEvents().stream().forEach(event -> {
					Path fileNameIn = (Path) event.context();

					Path filePathIn = Paths.get(pathIn.concat(fileNameIn.toString()));
					LOGGER.debug("Diretorio de escuta: {}", filePathIn);

					if (this.isCorrectFileType(fileNameIn)) {
						this.importFileService.process(filePathIn);
					}

				});

				boolean valid = key.reset();
				if (!valid) {
					LOGGER.warn("Key reset not valid!");
					break;
				}
			}
			watcher.close();
		} catch (IOException | InterruptedException exe) {
			LOGGER.error(exe.getMessage());
			LOGGER.error(Arrays.toString(exe.getStackTrace()));
		}

		LOGGER.info(" << watchDirectory");
	}

//	public void watchDirectoryPath(String pathIn) {
//		LOGGER.info(" >> watchDirectoryPath");
//
//		try {
//			WatchService watcher = FileSystems.getDefault().newWatchService();
//			Path dir = Paths.get(pathIn);
//			dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
//			dir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
//
//			LOGGER.debug("Watch Service registered for dir: {}", dir.toString());
//
//			int count = 0;
//
//			while (true) {
//
//				count++;
//
//				LOGGER.debug("Watch Service count: {}", count);
//				WatchKey key;
//
//				try {
//					key = watcher.take();
//				} catch (InterruptedException exe) {
//					LOGGER.error(exe.getMessage());
//					LOGGER.error(Arrays.toString(exe.getStackTrace()));
//					return;
//				}
//
//				for (WatchEvent<?> event : key.pollEvents()) {
//					WatchEvent.Kind<?> kind = event.kind();
//
//					@SuppressWarnings("unchecked")
//					WatchEvent<Path> eventWatch = (WatchEvent<Path>) event;
//					Path fileNameIn = eventWatch.context();
//
//					Path filePathIn = Paths.get(pathIn.concat(fileNameIn.toString()));
//
//					LOGGER.debug("Diretorio de escuta: {} : {}", kind.name(), fileNameIn);
//
//					if (kind == StandardWatchEventKinds.ENTRY_MODIFY || kind == StandardWatchEventKinds.ENTRY_CREATE) {
//						if (isCorrectFileType(fileNameIn)) {
//							this.importFileService.process(filePathIn);
//						}
//					}
//				}
//
//				boolean valid = key.reset();
//				if (!valid) {
//					LOGGER.warn("Key reset not valid!");
//					break;
//				}
//			}
//
//		} catch (IOException exe) {
//			LOGGER.error(exe.getMessage());
//			LOGGER.error(Arrays.toString(exe.getStackTrace()));
//		}
//
//		LOGGER.info(" << watchDirectoryPath");
//	}

	private boolean isCorrectFileType(Path fileIn) {
		return fileIn.toString().endsWith(this.config.getSuffixFilter());
	}

}
