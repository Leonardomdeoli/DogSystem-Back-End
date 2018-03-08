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
@Table(name = "tb_neighborhood")
@AttributeOverride(name = "id",column = @Column(name = "cod_neigh"))
public class NeighborhoodEntity extends BaseEntity<Long> {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "O campo bairro não pode ser vazio")
	@NotNull(message = "O campo bairro não pode ser nulo")
	@Column(name = "name", length = 30, nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "cod_city", nullable = false)
	private CityEntity city;
	
	
	public NeighborhoodEntity() {
	}

	public NeighborhoodEntity(String name, CityEntity city) {
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}
}
