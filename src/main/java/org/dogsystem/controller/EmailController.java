package org.dogsystem.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dogsystem.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicePath.EMAIL_PATH)
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;
	private final Logger LOGGER = LogManager.getLogger(this.getClass());

	@GetMapping
	public ResponseEntity<String> sendMail() {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setText("Hello from Spring Boot Application");
		message.setTo("leonardomdeoli@gmail.com");
		message.setFrom("leonardomdeoli@gmail.com");

		try {
			mailSender.send(message);
			
			return ResponseEntity.ok("Email enviado com sucesso!");
			
		} catch (Exception e) {
			LOGGER.error("Ocorreu erro ao enviar emial: " + e.getMessage());
			
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Erro ao enviar email");
		}
	}
}