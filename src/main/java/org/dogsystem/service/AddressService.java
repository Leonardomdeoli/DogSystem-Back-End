package org.dogsystem.service;

import org.dogsystem.entity.AddressEntity;
import org.dogsystem.entity.CityEntity;
import org.dogsystem.entity.NeighborhoodEntity;
import org.dogsystem.entity.UfEntity;
import org.dogsystem.entity.UserEntity;
import org.dogsystem.exception.ServerException;
import org.dogsystem.repository.AddressRepository;
import org.dogsystem.repository.CityRepository;
import org.dogsystem.repository.NeighborhoodRepository;
import org.dogsystem.repository.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private NeighborhoodRepository neighborhoodRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private UfRepository ufRepository;
	
	public AddressEntity getAddress(UserEntity user){
		AddressEntity addressEntity = user.getAddress();
		
		if (addressEntity == null) {
			String errorMessage = "Endereço não cadastrado";
			throw new ServerException(errorMessage);
		}

		return addressRepository.findByZipcode(addressEntity.getZipcode());
	}

	public AddressEntity addressBuild(AddressEntity address) {

		NeighborhoodEntity neighborhood = neighborhoodRepository.findByName(address.getNeighborhood().getName());
		if (neighborhood == null) {

			CityEntity city = cityRepository.findByName(address.getNeighborhood().getCity().getName());
			if (city == null) {

				UfEntity uf = ufRepository.findBySigla(address.getNeighborhood().getCity().getUf().getSigla());
				if (uf == null) {
					uf = ufRepository.save(address.getNeighborhood().getCity().getUf());
				}
				city = address.getNeighborhood().getCity();
				city.setUf(uf);
				
				city.setId(null);
				city = cityRepository.save(city);
			}

			neighborhood = address.getNeighborhood();
			neighborhood.setCity(city);
			
			neighborhood.setId(null);
			neighborhood = neighborhoodRepository.save(neighborhood);
		}
		address.setNeighborhood(neighborhood);
		
		address.setId(null);
		return addressRepository.save(address);
	}

}
