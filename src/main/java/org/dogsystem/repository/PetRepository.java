package org.dogsystem.repository;

import org.dogsystem.entity.BreedEntity;
import org.dogsystem.entity.PetEntity;
import org.dogsystem.enumeration.Ativo;
import org.dogsystem.enumeration.Sex;
import org.dogsystem.enumeration.TipoAnimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<PetEntity, Long>{

	public Page<PetEntity> findBySexAndBreedAndUsadogLove(Sex sex, BreedEntity breed,Ativo ativo,Pageable pageable);

	public Page<PetEntity> findByTipoAnimal(TipoAnimal animal,Pageable pageable);

	public Page<PetEntity> findByNameStartingWith(String name,Pageable pageable);

	public Page<PetEntity> findByNameStartingWithAndTipoAnimal(String name, TipoAnimal animal,Pageable pageable);

	public Page<PetEntity> findByAmigoPet(Ativo ativo, Pageable pageable);
	
}
