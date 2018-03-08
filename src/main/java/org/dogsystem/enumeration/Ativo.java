package org.dogsystem.enumeration;

public enum Ativo {
	True(true), False(false);

	boolean ativo;

	Ativo(boolean s) {
		ativo = s;
	}

	boolean getSize() {
		return ativo;
	}
}
