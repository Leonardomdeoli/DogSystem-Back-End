package org.dogsystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.dogsystem.entity.PetEntity;
import org.dogsystem.enumeration.Sex;
import org.dogsystem.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
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

	@SuppressWarnings("unchecked")
	public List<PetEntity> getPetSexoRaca(Sex sexo, Integer codBreed) {
		EntityManager session = null;
		
		List<PetEntity> pet = null;
		try {
			
			pet =  new ArrayList<PetEntity>();
			
			session = entityManagerFactory.createEntityManager();

			StringBuffer sql = new StringBuffer();
			sql.append(" select ");
			sql.append("	*  ");
			sql.append(" from  ");
			sql.append("	tb_pet ");
			sql.append(" where ");
			sql.append("	cod_breed = :CODBREED ");
			sql.append("	and sex = :SEX ");
			sql.append("	and usa_dog_love = 'T' ");

			Query query = (Query) session.createNativeQuery(sql.toString(), PetEntity.class);
			
			query.setParameter("SEX", sexo.ordinal());
			query.setParameter("CODBREED", codBreed);

			pet = query.getResultList();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return pet;
	}
}
