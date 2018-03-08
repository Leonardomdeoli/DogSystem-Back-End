package org.dogsystem.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.dogsystem.utils.BaseEntity;

@Entity
@Table(name = "tb_uf")
@AttributeOverride(name = "id", column = @Column(name = "cod_uf"))
public class UfEntity extends BaseEntity<Long>{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "sigla", columnDefinition = "char(2)", nullable = false, unique = true)
	private String sigla;
	
	public UfEntity() {
	}
	
	public UfEntity(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
