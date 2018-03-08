package org.dogsystem.enumeration;

public enum Size {
	Pequeno('P'), Medio('M'), Grande('G'), Gigante('A');

	char size;

	Size(char s) {
		size = s;
	}

	char getSize() {
		return size;
	}
}
