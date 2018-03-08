package org.dogsystem.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_agenda_service")
@AttributeOverride(name = "id", column = @Column(name = "cod_agen_serv"))
public class AgendaServiceEntity extends Agenda{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "cod_service", nullable = false)
	private ServicesEntity service;
	
	@Column(length = 5, nullable=false)
	private String time;


	public ServicesEntity getService() {
		return service;
	}
	public void setService(ServicesEntity service) {
		this.service = service;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
