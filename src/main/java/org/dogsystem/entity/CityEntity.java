package org.dogsystem.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dogsystem.utils.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tb_city")
@AttributeOverride(name = "id", column = @Column(name = "cod_city"))
public class CityEntity extends BaseEntity<Long>{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "O campo cidade não pode ser nulo")
	@NotEmpty(message = "O campo cidade não pode ser vazio")
	@Column(name = "name", length = 25, nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "cod_uf", nullable = false)
	private UfEntity uf;
	
	public CityEntity() {
	}

	public CityEntity(String name, UfEntity uf) {
		this.name = name;
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}
}
