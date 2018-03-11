package org.dogsystem.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dogsystem.entity.BreedEntity;
import org.dogsystem.enumeration.TipoAnimal;
import org.dogsystem.service.BreedService;
import org.dogsystem.utils.Message;
import org.dogsystem.utils.ServiceMap;
import org.dogsystem.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	private final Logger LOGGER = LogManager.getLogger(this.getClass());
	
	private Message<BreedEntity> message = new Message<BreedEntity>();

	@GetMapping
	public List<BreedEntity> findAll() {
		LOGGER.info("Solicitando todos as raças");
		return breedService.findAll();
	}

	@GetMapping(value = "/criteria/{criteria}/param/{param}/pagina/{pagina}/qtd/{qtd}")
	public Page<BreedEntity> findAllBreed(@PathVariable(name = "criteria") String criteria,
			@PathVariable(name = "param") TipoAnimal param, @PathVariable(name = "pagina") int pagina,
			@PathVariable(name = "qtd") int qtd) {
		LOGGER.info("Solicitando todos as raças");
		return breedService.findAllBreed(criteria, param,pagina, qtd);
	}

	@GetMapping(value = "/param/{param}/pagina/{pagina}/qtd/{qtd}")
	public Page<BreedEntity> findAllBreed(@PathVariable(name = "param") TipoAnimal param,@PathVariable(name = "pagina") int pagina,
			@PathVariable(name = "qtd") int qtd) {
		LOGGER.info("Solicitando todos as raças");
		return breedService.findAllBreed(null, param,pagina, qtd);
	}

	@GetMapping(value = "/criteria/{criteria}/pagina/{pagina}/qtd/{qtd}")
	public Page<BreedEntity> findAllBreed(@PathVariable(name = "criteria") String criteria,@PathVariable(name = "pagina") int pagina,
			@PathVariable(name = "qtd") int qtd) {
		LOGGER.info("Solicitando todos as raças");
		return breedService.findAllBreed(criteria, null,pagina, qtd);
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
