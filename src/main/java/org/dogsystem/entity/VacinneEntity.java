package org.dogsystem.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dogsystem.utils.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tb_vacinne")
@AttributeOverride(name = "id", column = @Column(name = "cod_vaccine"))
public class VacinneEntity extends BaseEntity<Long>{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "O campo descrição não pode ser nulo")
	@NotEmpty(message = "O campo descrição não pode ser vazio")
	@Column(name = "description",length = 25, nullable = false)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
