package org.dogsystem.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.dogsystem.entity.AddressEntity;
import org.dogsystem.entity.UserEntity;
import org.dogsystem.exception.ServerException;
import org.dogsystem.service.AddressService;
import org.dogsystem.service.UserService;
import org.dogsystem.utils.Message;
import org.dogsystem.utils.ServiceMap;
import org.dogsystem.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.USER_PATH)
public class UserController implements ServiceMap {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	Message<UserEntity> message = new Message<UserEntity>();

	@GetMapping
	public List<UserEntity> findAll() {
		LOGGER.info("Solicitando todos os de usuário registros.");
		return userService.getUsers();
	}

	@GetMapping(value = "/criteria/{criteria}")
	public List<UserEntity> findAllUser(@PathVariable(name = "criteria") String criteria) {
		LOGGER.info("Solicitando todos os usuários");
		return userService.getUser(criteria);
	}
	
	@GetMapping(value = "/name/{name}")
	public List<UserEntity> getUserName(@PathVariable(name = "name") String name) {
		LOGGER.info("Buscando por usuário " + name);
		return userService.getUser(name);
	}

	@GetMapping(value = "/id/{id}")
	public UserEntity getUser(@PathVariable(name = "id") Long id) {
		return userService.getUser(id);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Message<UserEntity>> insert(@RequestBody UserEntity user) {
		LOGGER.info(String.format("Solicitação de criação do usuário %s.", user));

		if (user == null) {
			String errorMessage = "Entidade com valor nulo";
			this.LOGGER.error(errorMessage);
			throw new ServerException(errorMessage);
		}

		AddressEntity address = addressService.getAddress(user);

		if (address == null) {
			address = addressService.addressBuild(user.getAddress());
		}

		user.setAddress(address);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setId(null);

		user = userService.save(user);

		message.AddField("mensagem", String.format(" O usuário %s foi salvo com sucesso", user.getName()));
		message.setData(user);

		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PutMapping
	public ResponseEntity<Message<UserEntity>> update(@RequestBody UserEntity user) {
		LOGGER.info(String.format("Solicitação de atualização do usuário %s.", user));

		if (user.getId() == null) {
			String errorMessage = String.format("ID do usuário %s não existe", user.getName());
			LOGGER.error(errorMessage);
			throw new ServerException(errorMessage);
		}

		AddressEntity address = addressService.getAddress(user);

		if (address == null) {
			address = addressService.addressBuild(user.getAddress());
		}

		user.setAddress(address);

		if(user.getPassword().length() < 10) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		user = userService.save(user);
		message.AddField("mensagem", String.format(" O usuário %s foi alterado com sucesso", user.getName()));
		message.setData(user);

		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity<Message<UserEntity>> deletar(@PathVariable(name = "id") Long id) {
		UserEntity user = userService.getUser(id);
		
		LOGGER.info(String.format("Pedido de exclusão do usuário %s.", user));

		userService.deleteUser(user);

		message.AddField("mensagem", String.format(" O usuário %s foi removido com sucesso", user.getName()));

		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
