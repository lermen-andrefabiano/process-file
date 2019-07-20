package br.com.processfile.util;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util implements Serializable {

	private static final long serialVersionUID = 449961409269050598L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

	public static void isDiretorioExists(String pathOut) {

		LOGGER.info("Verificando sem {} existe", pathOut);

		Path path = Paths.get(pathOut);

		File file = path.toFile();

		if (!file.exists()) {
			file.mkdirs();
		}
	}

}
