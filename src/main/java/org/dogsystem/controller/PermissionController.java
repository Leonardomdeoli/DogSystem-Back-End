package org.dogsystem.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.dogsystem.permission.PermissionEntity;
import org.dogsystem.service.PermissionService;
import org.dogsystem.utils.Message;
import org.dogsystem.utils.ServiceMap;
import org.dogsystem.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.PERMISSION_PATH)
public class PermissionController implements ServiceMap{
	
	@Autowired
	private PermissionService permissionService;

	private final Logger LOGGER = Logger.getLogger(this.getClass());
	
	private Message<PermissionEntity> message =  new Message<PermissionEntity>();

	@GetMapping
	public List<PermissionEntity> findAll() {
		LOGGER.info("Solicitando todos as permissões");
		return permissionService.findAll();
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Message<PermissionEntity>> insert(@RequestBody PermissionEntity permission) {
		LOGGER.info(String.format("Solicitação de criação da permissão %s.", permission.getRole()));
		permission.setId(null);
		permission = permissionService.save(permission);
		
		message.AddField("mensagem",String.format(" A permissao %s foi salvo com sucesso", permission.getRole()));
		message.setData(permission);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PutMapping
	public ResponseEntity<Message<PermissionEntity>> update(@RequestBody PermissionEntity permission) {
		LOGGER.info(String.format("Solicitação de atualização da permissão %s.", permission.getRole()));
		permission = permissionService.save(permission);

		message.AddField("mensagem",String.format(" A permissao %s foi alterada com sucesso", permission.getRole()));
		message.setData(permission);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@DeleteMapping
	public ResponseEntity<Message<PermissionEntity>> deletar(@RequestBody PermissionEntity permission) {
		LOGGER.info(String.format("Pedido de exclusão da permissão %s.", permission.getRole()));
		permissionService.delete(permission);

		message.AddField("mensagem",String.format(" A permissao %s foi apagada com sucesso", permission.getRole()));
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

}
