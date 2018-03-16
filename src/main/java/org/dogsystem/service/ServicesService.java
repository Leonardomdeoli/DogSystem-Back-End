package org.dogsystem.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.dogsystem.entity.ServicesEntity;
import org.dogsystem.enumeration.Porte;
import org.dogsystem.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {

	@Autowired
	private ServicesRepository serviceRepo;

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	public List<ServicesEntity> findAll() {
		LOGGER.info("Buscando todos os serviços.");
		return serviceRepo.findAll();
	}

	public void delete(ServicesEntity service) {
		this.LOGGER.debug(String.format("Excluindo o serviço [%s].", service.getName()));
		serviceRepo.delete(service);
	}

	public ServicesEntity save(ServicesEntity service) {
		LOGGER.info(String.format("Salvando o serviço [%s].", service.getName()));
		return serviceRepo.save(service);
	}

	public ServicesEntity findById(Long id) {
		return serviceRepo.findOne(id);
	}

	public List<ServicesEntity> findAllServices(String name, Porte porte) {
		LOGGER.info("Buscando todos os serviços.");
		
		if (name == null) {
			return serviceRepo.findBySize(porte);
		}

		if (porte == null) {
			return serviceRepo.findByNameStartingWith(name);
		}
		
		return serviceRepo.findByNameStartingWithAndSize(name, porte);
	}
}
