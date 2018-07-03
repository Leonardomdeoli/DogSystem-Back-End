package org.dogsystem.enumeration;

public enum Porte {
	Pequeno('P'), Médio('M'), Grande('G'), Gigante('A');

	char size;

	Porte(char s) {
		size = s;
	}

	char getSize() {
		return size;
	}
}
