package org.dogsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dogsystem.utils.BaseEntity;

@MappedSuperclass
public abstract class Agenda extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date schedulingDate;

	@Temporal(TemporalType.DATE)
	private Date lowDate;

	@Column(name = "note", length = 100)
	private String note;

	@ManyToOne
	@JoinColumn(name = "cod_pet", nullable = false)
	private PetEntity pet;

	public Date getSchedulingDate() {
		return schedulingDate;
	}

	public void setSchedulingDate(Date schedulingDate) {
		this.schedulingDate = schedulingDate;
	}

	public Date getLowDate() {
		return lowDate;
	}

	public void setLowDate(Date lowDate) {
		this.lowDate = lowDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public PetEntity getPet() {
		return pet;
	}

	public void setPet(PetEntity pet) {
		this.pet = pet;
	}
}
