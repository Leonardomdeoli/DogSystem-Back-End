package org.dogsystem.repository;

import org.dogsystem.entity.UfEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UfRepository extends JpaRepository<UfEntity,Long>{
	
	public UfEntity findBySigla(String sigla);
	
}
