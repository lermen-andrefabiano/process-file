package br.com.processfile.enumerated;

public enum FileTipo {

	UM("001"), DOIS("002"), TRES("003");

	private final String value;

	private FileTipo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
