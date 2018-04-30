package org.dogsystem.repository;

import java.util.List;

import org.dogsystem.entity.BreedEntity;
import org.dogsystem.enumeration.TipoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<BreedEntity, Long> {

	public List<BreedEntity> findByTipoAnimal(TipoAnimal animal);

	public List<BreedEntity> findByNameStartingWithAndTipoAnimal(String name, TipoAnimal animal);

	public List<BreedEntity> findByNameStartingWith(String name);
}
