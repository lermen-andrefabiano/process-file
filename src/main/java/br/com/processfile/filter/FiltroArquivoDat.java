package br.com.processfile.filter;

import java.io.File;
import java.io.FilenameFilter;

public class FiltroArquivoDat implements FilenameFilter {

	private static final String DAT = ".dat";

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(DAT);
	}
}
