package org.dogsystem.enumeration;

public enum TipoAnimal {
	Cão('C'),
	Gato('G');
	
	char tipoAnimal;
	
	TipoAnimal(char s) {
		tipoAnimal = s;
	}
	
	char getTipoAnimal(){
		return tipoAnimal;
	}
}