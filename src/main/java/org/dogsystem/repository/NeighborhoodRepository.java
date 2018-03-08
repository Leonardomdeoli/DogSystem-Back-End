package org.dogsystem.repository;

import org.dogsystem.entity.NeighborhoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeighborhoodRepository extends JpaRepository<NeighborhoodEntity, Long> {

	public NeighborhoodEntity findByName(String name);
	
	
}
