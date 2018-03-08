package org.dogsystem.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_agenda_vancine")
@AttributeOverride(name = "id", column = @Column(name = "cod_agen_vacc"))
public class AgendaVacinneEntity extends Agenda{

	private static final long serialVersionUID = 1L;
	
	@OneToMany
	@JoinColumn(name = "cod_vaccine")
	private List<VacinneEntity> vacinnes;

	public List<VacinneEntity> getVacinnes() {
		return vacinnes;
	}

	public void setVacinnes(List<VacinneEntity> vacinnes) {
		this.vacinnes = vacinnes;
	}
	
	
}
