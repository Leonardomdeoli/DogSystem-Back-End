package org.dogsystem.repository;

import java.util.Date;
import java.util.List;

import org.dogsystem.entity.AgendaServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaServiceRepository extends JpaRepository<AgendaServiceEntity, Long> {
	
	public List<AgendaServiceEntity> findBySchedulingDate(Date schedulingDate);
	
//	@Query(value = "select o from AgendaServiceEntity a where a.pet IN (Select p.id from PetEntity p where p.user = :user", nativeQuery = true)
//	public List<AgendaServiceEntity> findByAgendaUser(@Param("user") Long id);
	
	public List<AgendaServiceEntity> findBySchedulingDateBetween(Date dataInicial, Date dataFinal);
	
	public List<AgendaServiceEntity> findBySchedulingDateGreaterThanEqual(Date dataInicial);
	
	public List<AgendaServiceEntity> findBySchedulingDateEquals(Date dataInicial);
}
