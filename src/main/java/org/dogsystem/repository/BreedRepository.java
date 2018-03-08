package org.dogsystem.repository;

import org.dogsystem.entity.BreedEntity;
import org.dogsystem.enumeration.TipoAnimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<BreedEntity,Long> {
	
	public Page<BreedEntity> findByTipoAnimal(TipoAnimal animal, Pageable pageable);
	
	public Page<BreedEntity> findByNameStartingWithAndTipoAnimal(String name, TipoAnimal animal, Pageable pageable);

	public Page<BreedEntity> findByNameStartingWith(String name, Pageable pageable);

}
