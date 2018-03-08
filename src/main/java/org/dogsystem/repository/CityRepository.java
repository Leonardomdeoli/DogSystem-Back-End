package org.dogsystem.repository;

import org.dogsystem.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity,Long> {

	public CityEntity findByName(String name);
	
}
