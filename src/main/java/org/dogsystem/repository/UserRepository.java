package org.dogsystem.repository;

import java.util.List;

import org.dogsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByEmail(String email);
	
	public UserEntity findById(Long id);
	
	public UserEntity findByEmailOrName(String email, String name);
	
	public UserEntity findByNameContaining(String name);
	
	public List<UserEntity> findByNameStartingWith(String name);
}
