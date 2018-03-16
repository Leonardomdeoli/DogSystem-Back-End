package org.dogsystem.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.dogsystem.entity.PetEntity;
import org.dogsystem.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
	
	@Autowired
	private PetRepository petRepository;

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	public List<PetEntity> findAll() {
		LOGGER.info("Buscando todos os Animais.");
		return petRepository.findAll();
	}

	public void delete(PetEntity pet) {
		this.LOGGER.debug(String.format("Excluindo o animal [%s].", pet.getName()));
		petRepository.delete(pet);
	}

	public PetEntity save(PetEntity pet) {
		LOGGER.info(String.format("Salvando o animal [%s].", pet.getName()));
		return petRepository.save(pet);
	}
	
	public PetEntity findById(Long id){
		return petRepository.findOne(id);
	}

	public PetEntity findyPet(Long id) {
		return petRepository.findOne(id);
	}
}
