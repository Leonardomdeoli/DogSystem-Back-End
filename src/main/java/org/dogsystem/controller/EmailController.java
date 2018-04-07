package org.dogsystem.controller;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.dogsystem.entity.UserEntity;
import org.dogsystem.repository.UserRepository;
import org.dogsystem.service.UserService;
import org.dogsystem.utils.Message;
import org.dogsystem.utils.RandomAlphaNumeric;
import org.dogsystem.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ServicePath.EMAIL_PATH)
public class EmailController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	private final Logger LOGGER = Logger.getLogger(this.getClass());
	
	private Message<String> message = new Message<String>();

	@GetMapping
	public ResponseEntity<Message<String>> sendMail(@RequestParam("email") String email) {
		try {
			UserEntity user = this.userRepository.findByEmail(email);

			if (user == null) {
				throw new Exception("Email digitado não foi encontrado em nosso sistema.");
			}

			String senha = RandomAlphaNumeric.randomString(8);

			user.setPassword(passwordEncoder.encode(senha));

			String htmlMsg = "";
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader("src/main/resources/template/recupera-senha.html"));
				while (br.ready()) {
					htmlMsg += br.readLine();
				}
			} catch (Exception e) {
				LOGGER.error("Erro ao ler template " + e.getMessage());
				throw new Exception("Erro interno ao recuperar senha, informe a administração.");
			} finally {
				if (br != null) {
					br.close();
				}
			}

			htmlMsg = htmlMsg.replace("@username@", user.getName());
			htmlMsg = htmlMsg.replace("@host@", "localhost:8081");
			htmlMsg = htmlMsg.replace("@senha@", senha);

			buildEmail(user.getEmail(), htmlMsg, "Sistema de Troca Senha - Recuperar Senha");

			userService.save(user);
			
			message.AddField("mensagem", "Email enviado com sucesso, verifique em sua caixa e e-mail");
			return ResponseEntity.ok(message);
		} catch (Exception e) {
			LOGGER.error("Ocorreu erro ao enviar email: " + e.getMessage());
			message.AddField("mensagem", "Ocorreu erro ao enviar email, tente novamente mais tarde");

			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	private void buildEmail(String email, String message, String subject) throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		mimeMessage.setContent(message, "text/html");

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		helper.setTo(email);
		helper.setSubject(subject);

		mailSender.send(mimeMessage);
	}
}