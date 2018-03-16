package org.dogsystem.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.dogsystem.entity.ServicesEntity;
import org.dogsystem.enumeration.Porte;
import org.dogsystem.service.ServicesService;
import org.dogsystem.utils.Message;
import org.dogsystem.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.SERVICES_PATH)
public class ServicesController {

	@Autowired
	private ServicesService services;

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	private Message<ServicesEntity> message = new Message<ServicesEntity>();

	@GetMapping
	public List<ServicesEntity> findAll() {
		LOGGER.info("Solicitando todos os serviços");
		return services.findAll();
	}

	@GetMapping(value = "/criteria/{criteria}/param/{param}")
	public List<ServicesEntity> findAllBreed(@PathVariable(name = "criteria") String criteria, @PathVariable(name = "param") Porte param) {
		LOGGER.info("Solicitando todos as raças");
		return services.findAllServices(criteria, param);
	}

	@GetMapping(value = "/param/{param}")
	public List<ServicesEntity> findAllBreed(@PathVariable(name = "param") Porte param) {
		LOGGER.info("Solicitando todos as raças");
		return services.findAllServices(null, param);
	}

	@GetMapping(value = "/criteria/{criteria}")
	public List<ServicesEntity> findAllBreed(@PathVariable(name = "criteria") String criteria) {
		LOGGER.info("Solicitando todos as raças");
		return services.findAllServices(criteria, null);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Message<ServicesEntity>> insert(@RequestBody ServicesEntity service) {
		LOGGER.info(String.format("Solicitação de criação do serviço %s.", service.getName()));
		service.setId(null);

		service = services.save(service);

		message.AddField("mensagem", String.format(" O serviço %s foi salvo com sucesso", service.getName()));
		message.setData(service);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PutMapping
	public ResponseEntity<Message<ServicesEntity>> update(@RequestBody ServicesEntity service) {
		LOGGER.info(String.format("Solicitação de atualização do serviço %s.", service.getName()));

		service = services.save(service);

		message.AddField("mensagem", String.format(" O serviço %s foi alterada com sucesso", service.getName()));
		message.setData(service);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@DeleteMapping
	public ResponseEntity<Message<ServicesEntity>> deletar(@RequestBody ServicesEntity service) {
		LOGGER.info(String.format("Pedido de exclusão do serviço %s.", service.getName()));
		services.delete(service);

		message.AddField("mensagem", String.format(" O serviço %s foi apagada com sucesso", service.getName()));
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
