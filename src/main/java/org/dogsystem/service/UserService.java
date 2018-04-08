package org.dogsystem.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.dogsystem.entity.UserEntity;
import org.dogsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final Logger LOGGER = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserEntity> getUsers(){
		LOGGER.info("Buscandos todos os usuários.");
		return userRepository.findAll();
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
	
	public List<UserEntity> getUser(String name){
		LOGGER.info("Buscando todos os usuários com o nome " + name);
		return userRepository.findByNameStartingWith(name);
	}
	
}
