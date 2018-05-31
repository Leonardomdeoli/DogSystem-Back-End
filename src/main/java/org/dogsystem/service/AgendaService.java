package org.dogsystem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.dogsystem.entity.AgendaServiceEntity;
import org.dogsystem.repository.AgendaServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {

	@Autowired
	private AgendaServiceRepository agendaRepository;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

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

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	public List<AgendaServiceEntity> findAll() {
		LOGGER.info("Buscando todos os agendamentos.");
		return agendaRepository.findAll();
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

	@SuppressWarnings("unchecked")
	public List<AgendaServiceEntity> findByAgendamento(Date dataInicial, Date dataFinal, Integer codPet,
			Integer codService) {

		List<AgendaServiceEntity> agenda = null;
		EntityManager session = null;
		try {
			session = entityManagerFactory.createEntityManager();

			StringBuffer sql = new StringBuffer();
			sql.append(" select * from tb_agenda_service ");
			sql.append(" where ");

			if (dataFinal != null) {
				sql.append(" between :DATAINCIAL and :DATAFINAL ");
			}else {
				sql.append(" scheduling_date >= :DATAINCIAL ");
			}
			
			if (codPet != null) {
				sql.append(" and cod_pet = :CODPET ");
			}

			if (codService != null) {
				sql.append(" and cod_service = :CODSERVICE ");
			}


			Query query = (Query) session.createNativeQuery(sql.toString(), AgendaServiceEntity.class);
			query.setParameter("DATAINCIAL", dataInicial);

			if (codPet != null) {
				query.setParameter("CODPET", codPet);
			}

			if (codService != null) {
				query.setParameter("CODSERVICE", codService);
			}

			if (dataFinal != null) {
				query.setParameter("DATAFINAL", dataInicial);
			}

			agenda = query.getResultList();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return agenda;
	}
}
