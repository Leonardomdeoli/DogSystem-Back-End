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
@Table(name = "tb_address")
@AttributeOverride(name = "id", column = @Column(name = "cod_address"))
public class AddressEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "O campo logradouro não pode ser nulo")
	@NotEmpty(message = "O campo logradouro não pode ser vazio")
	@Column(length = 30, nullable = false)
	private String name;
	
	@NotNull(message = "O campo CEP não pode ser nulo")
	@NotEmpty(message = "O campo CEP não pode ser vazio")
	@Column(length = 9, nullable = false)
	private String zipcode;
	
	@ManyToOne
	@JoinColumn(name = "cod_neigh", nullable = false)
	private NeighborhoodEntity neighborhood;	
	
	public AddressEntity() {
	}

	public AddressEntity(String name, String zipcode, NeighborhoodEntity neighborhood) {
		this.name = name;
		this.zipcode = zipcode;
		this.neighborhood = neighborhood;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public NeighborhoodEntity getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(NeighborhoodEntity neighborhood) {
		this.neighborhood = neighborhood;
	}
}