package org.dogsystem.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.dogsystem.permission.PermissionEntity;
import org.dogsystem.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
	@Autowired
	private PermissionRepository permissionRepository;

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	public List<PermissionEntity> findAll() {
		LOGGER.info("Buscando todas as permissões.");
		return permissionRepository.findAll();
	}

	public void delete(PermissionEntity permission) {
		this.LOGGER.debug(String.format("Excluindo a permissão [%s].", permission.getRole()));
		permissionRepository.delete(permission);
	}

	public PermissionEntity save(PermissionEntity permission) {
		LOGGER.info(String.format("Salvando o permissão [%s].", permission.getRole()));
		return permissionRepository.save(permission);
	}
}
