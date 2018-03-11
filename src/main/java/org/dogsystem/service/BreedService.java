package org.dogsystem.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.dogsystem.entity.BreedEntity;
import org.dogsystem.enumeration.TipoAnimal;
import org.dogsystem.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BreedService {

	@Autowired
	private BreedRepository breedRepository;

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	public List<BreedEntity> findAll() {
		LOGGER.info("Buscando todas as raças.");
		return breedRepository.findAll();
	}

	public Page<BreedEntity> findAllBreed(String name, TipoAnimal animal, int pagina, int qtd) {
		LOGGER.info("Buscando todas as raças.");
		
		Pageable pageable  = new PageRequest(pagina, qtd);
		
		if (name == null) {
			return breedRepository.findByTipoAnimal(animal,pageable);
		}

		if (animal == null) {
			return breedRepository.findByNameStartingWith(name, pageable);
		}
		return breedRepository.findByNameStartingWithAndTipoAnimal(name, animal, pageable);
	}

	public BreedEntity findOne(Long id) {
		return breedRepository.findOne(id);
	}

	public void delete(BreedEntity breed) {
		this.LOGGER.debug(String.format("Excluindo a raça [%s].", breed.getName()));
		breedRepository.delete(breed);
	}

	public BreedEntity save(BreedEntity breed) {
		LOGGER.info(String.format("Salvando o raça [%s].", breed.getName()));
		return breedRepository.save(breed);
	}

}
