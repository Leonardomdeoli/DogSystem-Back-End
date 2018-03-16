package org.dogsystem.repository;

import java.util.List;

import org.dogsystem.entity.ServicesEntity;
import org.dogsystem.enumeration.Porte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<ServicesEntity, Long> {

	public List<ServicesEntity> findByNameStartingWithAndSize(String name, Porte size);

	public List<ServicesEntity> findBySize(Porte size);

	public List<ServicesEntity> findByNameStartingWith(String name);

}
