package org.dogsystem.repository;

import java.util.Date;
import java.util.List;

import org.dogsystem.entity.AgendaServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgendaServiceRepository extends JpaRepository<AgendaServiceEntity, Long> {
	
	public List<AgendaServiceEntity> findBySchedulingDate(Date schedulingDate);
	
//	@Query(value = "select o from AgendaServiceEntity a where a.pet IN (Select p.id from PetEntity p where p.user = :user", nativeQuery = true)
//	public List<AgendaServiceEntity> findByAgendaUser(@Param("user") Long id);
	
	public Page<AgendaServiceEntity> findBySchedulingDateBetween(Date dataInicial, Date dataFinal, Pageable pageable);
	
	public Page<AgendaServiceEntity> findBySchedulingDateGreaterThanEqual(Date dataInicial,Pageable pageable);
	
	public Page<AgendaServiceEntity> findBySchedulingDateEquals(Date dataInicial, Pageable pageable);
}
