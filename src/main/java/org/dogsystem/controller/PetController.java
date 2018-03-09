package org.dogsystem.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.dogsystem.entity.PetEntity;
import org.dogsystem.service.ImageService;
import org.dogsystem.service.PetService;
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
@RequestMapping(path = ServicePath.PET_PATH)
public class PetController {

	@Autowired
	private PetService petService;

	@Autowired
	private ImageService imageService;

	private final Logger LOGGER = LogManager.getLogger(this.getClass());

	private Message<PetEntity> message = new Message<PetEntity>();

	@GetMapping
	public List<PetEntity> findAll() {
		LOGGER.info("Solicitando todos os animais");
		return petService.findAll();
	}

	@GetMapping(value = "/id/{id}")
	public Optional<PetEntity> findyPet(@PathVariable(name = "id") Long id) {
		LOGGER.info("Buscando animal de id " + id);
		return petService.findyPet(id);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Message<PetEntity>> insert(@RequestBody PetEntity pet) {
		LOGGER.info(String.format("Solicitação de criação do animal %s.", pet.getName()));
		pet.setId(null);
		pet = petService.save(pet);

		message.AddField("mensagem", String.format(" O animal %s foi salvo com sucesso", pet.getName()));
		message.setData(pet);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PutMapping
	public ResponseEntity<Message<PetEntity>> update(@RequestBody PetEntity pet) {
		LOGGER.info(String.format("Solicitação de atualização do animal %s.", pet.getName()));

		Optional<PetEntity> petOld = petService.findById(pet.getId());
		if (petOld.isPresent()) {
			if (petOld.get().getImage().getId() != pet.getImage().getId()) {
				imageService.delete(petOld.get().getImage());
			}
		}

		pet = petService.save(pet);

		message.AddField("mensagem", String.format(" O animal %s foi alterada com sucesso", pet.getName()));
		message.setData(pet);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@DeleteMapping
	public ResponseEntity<Message<PetEntity>> deletar(@RequestBody PetEntity pet) {
		LOGGER.info(String.format("Pedido de exclusão do animal %s.", pet.getName()));
		petService.delete(pet);

		message.AddField("mensagem", String.format(" O animal %s foi apagada com sucesso", pet.getName()));
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
