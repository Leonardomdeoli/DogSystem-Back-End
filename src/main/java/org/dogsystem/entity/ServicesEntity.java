package org.dogsystem.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dogsystem.enumeration.Porte;
import org.dogsystem.utils.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tb_service")
@AttributeOverride(name = "id", column = @Column(name = "cod_service"))
public class ServicesEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "O campo descrição não pode ser vazio")
	@NotNull(message = "O campo descrição não pode ser nulo")
	@Column(nullable = false)
	private String name;

	@Enumerated(EnumType.ORDINAL)
	private Porte size;

	@Column(columnDefinition = "decimal(20,2)")
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String description) {
		this.name = description;
	}

	public Porte getSize() {
		return size;
	}

	public void setSize(Porte size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
