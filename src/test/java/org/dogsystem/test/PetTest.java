
package org.dogsystem.test;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.dogsystem.controller.PetController;
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
public class PetTest {

	@Autowired
	private PetController petController;

	private MockMvc mockMvc;

	private JSONObject jsonObject;

	private static final Logger LOGGER = Logger.getLogger(PetTest.class);
	
	@Before
	public void init() throws IOException {
		mockMvc = MockMvcBuilders.standaloneSetup(petController).setControllerAdvice(new Exception()).build();
	}

	public void buscaPet() throws Exception {
		LOGGER.info("Inserindo usuário");
		
		mockMvc.perform(get(ServicePath.PET_PATH).contentType(APPLICATION_JSON)
				.content(jsonObject.toString().getBytes())).andDo(print()).andExpect(status().isOk());
		
		LOGGER.info("Teste efetuado com sucesso");

	}
}
