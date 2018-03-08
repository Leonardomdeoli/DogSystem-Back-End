package org.dogsystem.repository;

import org.dogsystem.entity.ServicesEntity;
import org.dogsystem.enumeration.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<ServicesEntity, Long> {

	public Page<ServicesEntity> findByNameStartingWithAndSize(String name, Size size, Pageable pageable);

	public Page<ServicesEntity> findBySize(Size size, Pageable pageable);

	public Page<ServicesEntity> findByNameStartingWith(String name, Pageable pageable);

}
