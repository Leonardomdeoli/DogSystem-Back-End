package org.dogsystem.service;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.dogsystem.entity.ServicesEntity;
import org.dogsystem.enumeration.Size;
import org.dogsystem.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {

	@Autowired
	private ServicesRepository serviceRepo;

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	public Page<ServicesEntity> findAll(int pagina, int qtd) {
		LOGGER.info("Buscando todos os serviços.");
		return serviceRepo.findAll(new PageRequest(pagina, qtd));
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

	public Page<ServicesEntity> findAllServices(String name, Size size,int pagina, int qtd) {
		LOGGER.info("Buscando todos os serviços.");
		
		Pageable pageable  = new PageRequest(pagina, qtd);

		if (name == null) {
			return serviceRepo.findBySize(size,pageable);
		}

		if (size == null) {
			return serviceRepo.findByNameStartingWith(name,pageable);
		}
		
		return serviceRepo.findByNameStartingWithAndSize(name, size,pageable);
	}
}
