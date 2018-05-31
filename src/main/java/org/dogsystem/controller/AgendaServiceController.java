package org.dogsystem.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.dogsystem.entity.AgendaServiceEntity;
import org.dogsystem.service.AgendaService;
import org.dogsystem.utils.Message;
import org.dogsystem.utils.ServicePath;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.AGENDA_PATH)
public class AgendaServiceController {

	@Autowired
	private AgendaService agendaService;

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	private Message<AgendaServiceEntity> message = new Message<AgendaServiceEntity>();

	@GetMapping
	public List<AgendaServiceEntity> findAll() {
		LOGGER.info("Solicitando todos os agendamento");
		return agendaService.findAll();
	}

	@GetMapping("/getAgendamentos")
	public List<AgendaServiceEntity> getAgendamentos(@RequestParam(value = "datainicial") Date dataInicial,
			@RequestParam(value = "datafinal", required = false) Date dataFinal,
			@RequestParam(value = "pet", required = false) Integer codPet,
			@RequestParam(value = "service", required = false) Integer codService) {
		return agendaService.findByAgendamento(dataInicial, dataFinal, codPet, codService);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Message<AgendaServiceEntity>> insert(@RequestBody AgendaServiceEntity agenda) {
		LOGGER.info("Solicitação do criação do agemdamento");
		agenda.setId(null);

		agenda = agendaService.save(agenda);

		message.AddField("mensagem", "O agendamento foi criado com sucesso.");
		message.setData(agenda);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PutMapping
	public ResponseEntity<Message<AgendaServiceEntity>> update(@RequestBody AgendaServiceEntity agenda) {

		agenda = agendaService.save(agenda);

		message.AddField("mensagem", " O agendamento foi alterada com sucesso");
		message.setData(agenda);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@DeleteMapping
	public ResponseEntity<Message<AgendaServiceEntity>> deletar(@RequestBody AgendaServiceEntity agenda) {
		agendaService.delete(agenda);

		message.AddField("mensagem", "O agendamento foi apagada com sucesso");
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
