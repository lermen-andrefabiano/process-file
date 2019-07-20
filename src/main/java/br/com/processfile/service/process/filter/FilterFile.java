package br.com.processfile.service.process.filter;

import java.io.File;
import java.io.FilenameFilter;

public class FilterFile implements FilenameFilter {

	private String suffixFilter;

	public FilterFile() {
		throw new IllegalArgumentException("Construtor não permitido");
	}

	public FilterFile(String suffixFilter) {
		this.suffixFilter = suffixFilter;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(this.suffixFilter);
	}

}
