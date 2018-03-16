package org.dogsystem.repository;

import java.util.List;

import org.dogsystem.entity.BreedEntity;
import org.dogsystem.entity.PetEntity;
import org.dogsystem.enumeration.Sex;
import org.dogsystem.enumeration.TipoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<PetEntity, Long>{

	public List<PetEntity> findBySexAndBreedAndUsaDogLove(Sex sex, BreedEntity breed,boolean usaDogLove);

	public List<PetEntity> findByTipoAnimal(TipoAnimal animal);

	public List<PetEntity> findByNameStartingWith(String name);

	public List<PetEntity> findByNameStartingWithAndTipoAnimal(String name, TipoAnimal animal);
}
