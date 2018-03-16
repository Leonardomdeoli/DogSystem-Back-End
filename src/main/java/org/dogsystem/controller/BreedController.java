package org.dogsystem.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.dogsystem.entity.BreedEntity;
import org.dogsystem.enumeration.TipoAnimal;
import org.dogsystem.service.BreedService;
import org.dogsystem.utils.Message;
import org.dogsystem.utils.ServiceMap;
import org.dogsystem.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = ServicePath.BREED_PATH)
public class BreedController implements ServiceMap {

	@Autowired
	private BreedService breedService;

	private final Logger LOGGER = Logger.getLogger(this.getClass());
	
	private Message<BreedEntity> message = new Message<BreedEntity>();

	@GetMapping
	public List<BreedEntity> findAll() {
		LOGGER.info("Solicitando todos as raças");
		return breedService.findAll();
	}

	@GetMapping(value = "/criteria/{criteria}/param/{param}")
	public List<BreedEntity> findAllBreed(@PathVariable(name = "criteria") String criteria,	@PathVariable(name = "param") TipoAnimal param) {
		LOGGER.info("Solicitando todos as raças");
		return breedService.findAllBreed(criteria, param);
	}

	@GetMapping(value = "/param/{param}")
	public List<BreedEntity> findAllBreed(@PathVariable(name = "param") TipoAnimal param) {
		LOGGER.info("Solicitando todos as raças");
		return breedService.findAllBreed(null, param);
	}

	@GetMapping(value = "/criteria/{criteria}")
	public List<BreedEntity> findAllBreed(@PathVariable(name = "criteria") String criteria) {
		LOGGER.info("Solicitando todos as raças");
		return breedService.findAllBreed(criteria, null);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Message<BreedEntity>> insert(@RequestBody BreedEntity breed) {
		LOGGER.info(String.format("Solicitação de criação da raça %s.", breed.getName()));
		breed.setId(null);
		breedService.save(breed);

		message.AddField("mensagem", String.format(" A raça %s foi salvo com sucesso", breed.getName()));
		message.setData(breed);
		return ResponseEntity.ok(message);
	}

	@PutMapping
	public ResponseEntity<Message<BreedEntity>> update(@RequestBody BreedEntity breed) {
		LOGGER.info(String.format("Solicitação de atualização da raça %s.", breed.getName()));
		breedService.save(breed);

		message.AddField("mensagem", String.format(" A raça %s foi alterada com sucesso", breed.getName()));
		message.setData(breed);
		return ResponseEntity.ok(message);
	}

	@DeleteMapping
	public ResponseEntity<Message<BreedEntity>> deletar(@RequestBody BreedEntity breed) {
		LOGGER.info(String.format("Pedido de exclusão da raça %s.", breed.getName()));
		breedService.delete(breed);

		message.AddField("mensagem", String.format(" A raça %s foi apagada com sucesso", breed.getName()));
		return ResponseEntity.ok(message);
	}
}
