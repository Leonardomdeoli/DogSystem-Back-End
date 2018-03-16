package org.dogsystem.enumeration;

public enum Ativo {
	SIM('S'), NAO('N');

	char ativo;

	Ativo(char s) {
		ativo = s;
	}

	public char getAtivo() {
		return ativo;
	}
}
