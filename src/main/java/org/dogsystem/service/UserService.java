package org.dogsystem.service;

import org.apache.log4j.Logger;
import org.dogsystem.entity.UserEntity;
import org.dogsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final Logger LOGGER = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<UserEntity> getUsers(int pagina, int qtd){
		LOGGER.info("Buscandos todos os usuários.");
		return userRepository.findAll(new PageRequest(pagina, qtd));
	}
	
	public void deleteUser(UserEntity user){
		LOGGER.info(String.format("Excluindo o usuário [%s].", user));
		userRepository.delete(user);		
	}

	public UserEntity save(UserEntity user) {
		LOGGER.info(String.format("Salvando o usuário [%s].", user.getName()));
		return userRepository.save(user);
	}
	
	public UserEntity getUser(Long id) {
		LOGGER.info("Buscando o usuário de código " + id);
		return userRepository.findById(id);
	}
	
	public Page<UserEntity> getUser(String name,int pagina, int qtd){
		LOGGER.info("Buscando todos os usuários com o nome " + name);
		return userRepository.findByNameStartingWith(name, new PageRequest(pagina, qtd));
	}
	
}
