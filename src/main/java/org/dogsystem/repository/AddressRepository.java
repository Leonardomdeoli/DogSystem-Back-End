package org.dogsystem.repository;

import org.dogsystem.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

	public AddressEntity findByName(String name);

	public AddressEntity findByZipcode(String zipcode);
	
}
