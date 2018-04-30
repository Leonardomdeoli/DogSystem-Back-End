
package org.dogsystem.test;

import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.dogsystem.controller.UserController;
import org.dogsystem.utils.ServicePath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	@Autowired
	private UserController usuarioController;

	private MockMvc mockMvc;

	private JSONObject jsonObject;
	private JSONParser parser = new JSONParser();

	private static final Logger LOGGER = Logger.getLogger(AppTest.class);
	
	@Before
	public void init() throws IOException {
		mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).setControllerAdvice(new Exception()).build();
	}

	//criando um novo usuario
	@Test
	public void teste1() throws Exception {
		
		LOGGER.info("Buscando Json com dados do usuário");
		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/usuario.json"));

		LOGGER.info("Inserindo usuário");
		mockMvc.perform(post(ServicePath.USER_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toString().getBytes())).andDo(print()).andExpect(status().isOk());
		
		LOGGER.info("Teste efetuado com sucesso");

	}

	//Teste de busca de usuario com id 1
	@Test
	public void teste2() throws Exception {
		LOGGER.info("Lendo usuario com id 1");
		
		mockMvc.perform(get(ServicePath.USER_PATH + "/id/1").accept(APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("name", is("leonardo"))).andExpect(jsonPath("email", is("leonardo2@gmail.com")));
		
		LOGGER.info("Teste efetuado com sucesso");
	}

	//Atualizando usuario com id 1  
	@Test
	public void teste3() throws Exception {
		LOGGER.info("Buscando Json com dados do usuário");		
		
		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/usuario.json"));

		LOGGER.info("Atualizando Json com dados do usuário");		
		jsonObject.put("email", "novo@gmail.com");
		jsonObject.put("phone", "98144821");

		LOGGER.info("Atualizando o usuário");		
		mockMvc.perform(put(ServicePath.USER_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toJSONString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Teste efetuado com sucesso");
	}

	//Buscando usuario com id 1
	@Test
	public void teste4() throws Exception {
		mockMvc.perform(get(ServicePath.USER_PATH + "/id/1")).andDo(print());
	}

	//removendo o usuario di 1
	@Test
	public void teste5() throws Exception {
		LOGGER.info("Removendo usuario com id 1");
		mockMvc.perform(delete(ServicePath.USER_PATH + "/id/1").accept(APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
		LOGGER.info("Teste efetuado com sucesso");
	}

}
