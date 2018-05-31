package org.dogsystem.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dogsystem.enumeration.Sex;
import org.dogsystem.enumeration.TipoAnimal;
import org.dogsystem.utils.BaseEntity;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "tb_pet")
@AttributeOverride(name = "id", column = @Column(name = "cod_pet"))
public class PetEntity extends BaseEntity<Long>{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name",length = 100)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_tbirth")
	private Date dateBirth;
	
	@Column(name = "note",length = 255)
	private String note;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fertile_period")
	private Date fertilePeriod;

	@OneToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name = "cod_image", nullable = false)
	private ImageEntity image;
	
	@ManyToOne
	@JoinColumn(name = "cod_breed")
	private BreedEntity breed;
	
	@ManyToOne
	@JoinColumn(name = "cod_owner", nullable = false)
	private UserEntity user;
	
	@Enumerated(EnumType.ORDINAL)
	private Sex sex;
	
	@Type(type="true_false")
	private boolean usaDogLove;

	@Enumerated(EnumType.ORDINAL)
	private TipoAnimal tipoAnimal;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getFertilePeriod() {
		return fertilePeriod;
	}

	public void setFertilePeriod(Date fertilePeriod) {
		this.fertilePeriod = fertilePeriod;
	}

	public ImageEntity getImage() {
		return image;
	}

	public void setImage(ImageEntity image) {
		this.image = image;
	}

	public BreedEntity getBreed() {
		return breed;
	}

	public void setBreed(BreedEntity breed) {
		this.breed = breed;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	public boolean isUsaDogLove() {
		return usaDogLove;
	}

	public void setUsaDogLove(boolean usaDogLove) {
		this.usaDogLove = usaDogLove;
	}

	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}
}
