package org.dogsystem.enumeration;

public enum Sex {
	macho('M'),
	femea('F');
	
	char sex;
	
	Sex(char s) {
		sex = s;
	}
	
	char getSex(){
		return sex;
	}
}