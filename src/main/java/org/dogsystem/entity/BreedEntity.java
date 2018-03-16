package org.dogsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.dogsystem.enumeration.Porte;
import org.dogsystem.enumeration.TipoAnimal;
import org.dogsystem.utils.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tb_breed")
public class BreedEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "O campo raça não pode ser nulo")
	@NotEmpty(message = "O campo raça não pode ser vazio")
	@Size(min = 2, max = 120, message =  "O campo raça deve ter no minímo 2 caracteres")
	@Column(name = "name",length = 120 ,nullable = false, unique = true)
	private String name;
	
	private String life;
	
	private String height;
	
	private String weight;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoAnimal tipoAnimal;
	
	@Enumerated(EnumType.ORDINAL)
	private Porte porte;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLife() {
		return life;
	}

	public void setLife(String life) {
		this.life = life;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public Porte getPorte() {
		return porte;
	}

	public void setPorte(Porte porte) {
		this.porte = porte;
	}
}