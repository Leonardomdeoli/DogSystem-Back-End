package org.dogsystem.repository;

import java.util.Optional;

import org.dogsystem.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByEmail(String email);
	
	public Optional<UserEntity> findById(Long id);
	
	public UserEntity findByEmailOrName(String email, String name);
	
	public UserEntity findByNameContaining(String name);
	
	public Page<UserEntity> findByNameStartingWith(String name, Pageable pageable);
}
