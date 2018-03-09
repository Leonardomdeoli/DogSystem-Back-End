package org.dogsystem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.dogsystem.entity.AgendaServiceEntity;
import org.dogsystem.repository.AgendaServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {

	@Autowired
	private AgendaServiceRepository agendaRepository;

	private static List<String> times;

	static {
		times = new ArrayList<String>();
		times.add("08:00");
		times.add("09:00");
		times.add("10:00");
		times.add("11:00");
		times.add("12:00");
		times.add("13:00");
		times.add("14:00");
		times.add("15:00");
		times.add("16:00");
		times.add("17:00");
		times.add("18:00");
	}

	private final Logger LOGGER = LogManager.getLogger(this.getClass());

	public Page<AgendaServiceEntity> findAll(int pagina, int qtd) {
		LOGGER.info("Buscando todos os agendamentos.");
		return agendaRepository.findAll(new PageRequest(pagina, qtd));
	}

	public List<String> findByTime(Date date) {
		List<AgendaServiceEntity> agServs = agendaRepository.findBySchedulingDate(date);

		List<String> list = times;

		for (AgendaServiceEntity agSer : agServs) {
			list.remove(agSer.getTime());
		}
		return list;
	}

	public void delete(AgendaServiceEntity agenda) {
		agendaRepository.delete(agenda);
	}

	public AgendaServiceEntity save(AgendaServiceEntity agenda) {
		return agendaRepository.save(agenda);
	}

	public Page<AgendaServiceEntity> findByAgendamento(Date dataInicial, Date dataFinal, int pagina, int qtd) {
		if (dataFinal == null) {
			return agendaRepository.findBySchedulingDateEquals(dataInicial, new PageRequest(pagina, qtd));
		}
		return agendaRepository.findBySchedulingDateBetween(dataInicial, dataFinal, new PageRequest(pagina, qtd));
	}
}
