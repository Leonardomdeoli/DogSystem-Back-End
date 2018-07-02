package org.dogsystem.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.dogsystem.entity.UserEntity;
import org.dogsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final Logger LOGGER = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public List<UserEntity> getUsers(){
		LOGGER.info("Buscandos todos os usuários.");
		return userRepository.findAll();
	}
	
	public void deleteUser(UserEntity user){
		LOGGER.info(String.format("Excluindo o usuário [%s].", user));
		userRepository.delete(user);		
	}

	public UserEntity save(UserEntity user) {
		LOGGER.info(String.format("Salvando o usuário [%s].", user.getName()));
		return userRepository.save(user);
	}
	
	public UserEntity getUser(Long id) {
		LOGGER.info("Buscando o usuário de código " + id);
		return userRepository.findById(id);
	}
	
	public List<UserEntity> getUser(String name){
		LOGGER.info("Buscando todos os usuários com o nome " + name);
		return userRepository.findByNameStartingWith(name);
	}
	
	public boolean existsPet(UserEntity user) throws Exception {

		EntityManager session = null;
		try {
			session = entityManagerFactory.createEntityManager();

			StringBuffer sql = new StringBuffer();
			sql.append(" select count(*) from tb_pet where cod_owner = :CODUSER");

			Query query = (Query) session.createNativeQuery(sql.toString());

			query.setParameter("CODUSER", user.getId());

			Object obj =  query.getSingleResult();
			
			System.out.println(obj);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return true;
	}
	
}
