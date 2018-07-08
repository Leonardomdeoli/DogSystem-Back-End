
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

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.dogsystem.controller.AgendaServiceController;
import org.dogsystem.controller.BreedController;
import org.dogsystem.controller.EmailController;
import org.dogsystem.controller.PetController;
import org.dogsystem.controller.ServicesController;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Leonardo Mendes O plano de teste do sistema DogSystem foi definido
 *         para identificar falhas nas requisições HTTP, como por exemplo os
 *         métodos POST, GET, PUT e DELETE. Para essa situação, foi utilizado
 *         MockMvcBuilders que possui classes específicas para apoiar esse tipo
 *         de teste. E, todas as classes rest ou controller do sistema possuem
 *         validações para garantir sua integridade.
 */
@SuppressWarnings("unchecked")
// Sorts by method name
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class APPTest {

	@Autowired
	private UserController usuarioController;

	@Autowired
	private PetController petController;

	@Autowired
	private BreedController breedController;

	@Autowired
	private ServicesController servicesController;

	@Autowired
	private EmailController emailController;

	@Autowired
	private AgendaServiceController agendaController;

	private MockMvc mockMvcUsuario;
	private MockMvc mockMvcPet;
	private MockMvc mockMvcBreed;
	private MockMvc mockMvcService;
	private MockMvc mockMvcEmail;
	private MockMvc mockMvcAgendamento;

	private JSONObject jsonObject;
	private JSONParser parser = new JSONParser();

	private static final Logger LOGGER = Logger.getLogger(APPTest.class);

	@Before
	public void init() throws IOException {
		mockMvcUsuario = MockMvcBuilders.standaloneSetup(usuarioController).setControllerAdvice(new Exception())
				.build();

		mockMvcPet = MockMvcBuilders.standaloneSetup(petController).setControllerAdvice(new Exception()).build();

		mockMvcBreed = MockMvcBuilders.standaloneSetup(breedController).setControllerAdvice(new Exception()).build();

		mockMvcService = MockMvcBuilders.standaloneSetup(servicesController).setControllerAdvice(new Exception())
				.build();

		mockMvcEmail = MockMvcBuilders.standaloneSetup(emailController).setControllerAdvice(new Exception()).build();

		mockMvcAgendamento = MockMvcBuilders.standaloneSetup(agendaController).setControllerAdvice(new Exception())
				.build();
	}

	@Test
	public void test01() throws Exception {
		LOGGER.info("Inicio teste POST usuário");

		LOGGER.info("Buscando Json com dados do usuário");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/usuario.json"));

		LOGGER.info("Inserindo usuário");

		mockMvcUsuario.perform(post(ServicePath.USER_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Fim teste POST usuário");

	}

	@Test
	public void test02() throws Exception {
		LOGGER.info("Inicio teste POST raça");

		LOGGER.info("Buscando Json com dados do Raça");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/breed.json"));

		LOGGER.info("Inserindo Raça");

		mockMvcBreed.perform(post(ServicePath.BREED_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Fim teste POST raça");

	}

	@Test
	public void test03() throws Exception {
		LOGGER.info("Inicio teste POST animal");

		LOGGER.info("Buscando Json com dados do animal");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/pet.json"));

		LOGGER.info("Inserindo Animal");

		mockMvcPet.perform(post(ServicePath.PET_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Fim teste POST pet");

	}

	@Test
	public void test04() throws Exception {
		LOGGER.info("Inicio teste POST Serviços");

		LOGGER.info("Buscando Json com dados do serviços");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/service.json"));

		LOGGER.info("Inserindo Serviço");

		mockMvcService.perform(post(ServicePath.SERVICES_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Fim teste POST pet");

	}

	@Test
	public void test05() throws Exception {
		LOGGER.info("Inicio teste GET Usuario");

		LOGGER.info("Lendo usuário com id 1");

		mockMvcUsuario.perform(get(ServicePath.USER_PATH + "/id/1").accept(APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("name", is("leonardo"))).andExpect(jsonPath("email", is("leonardo2@gmail.com")));

		LOGGER.info("Teste efetuado GET sucesso");
	}

	@Test
	public void test06() throws Exception {
		LOGGER.info("Inicio teste GET Raça");

		LOGGER.info("Buscando todas as raças");

		mockMvcBreed.perform(get(ServicePath.BREED_PATH).accept(APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8));

		LOGGER.info("Teste efetuado GET sucesso");
	}

	@Test
	public void test07() throws Exception {
		LOGGER.info("Inicio teste GET pet");

		LOGGER.info("Lendo animal com id 1");

		mockMvcPet.perform(get(ServicePath.PET_PATH + "/id/1").accept(APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("sex", is("femea"))).andExpect(jsonPath("name", is("Abelha")));

		LOGGER.info("Teste efetuado GET sucesso");
	}

	@Test
	public void test08() throws Exception {
		LOGGER.info("Inicio teste GET Service");

		LOGGER.info("Lendo Raça com id 1");

		mockMvcService.perform(get(ServicePath.SERVICES_PATH).accept(APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8));

		LOGGER.info("Teste efetuado GET sucesso");
	}

	@Test
	public void test09() throws Exception {
		LOGGER.info("Inicio teste PUT Usuário");

		LOGGER.info("Buscando Json com dados do usuário");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/usuario.json"));

		LOGGER.info("Atualizando Json com dados do usuário");
		jsonObject.put("email", "novo@gmail.com");
		jsonObject.put("phone", "98144821");

		LOGGER.info("Atualizando o usuário");
		mockMvcUsuario.perform(put(ServicePath.USER_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toJSONString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Teste PUT efetuado com sucesso");
	}

	@Test
	public void test10() throws Exception {
		LOGGER.info("Inicio teste PUT Pet");

		LOGGER.info("Buscando Json com dados do Pet");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/pet.json"));

		LOGGER.info("Atualizando Json com dados do Pet");
		jsonObject.put("name", "Raje");

		LOGGER.info("Atualizando o Pet");
		mockMvcPet.perform(put(ServicePath.PET_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toJSONString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Teste PUT efetuado  com sucesso");
	}

	@Test
	public void test11() throws Exception {
		LOGGER.info("Inicio teste PUT Raça");

		LOGGER.info("Buscando Json com dados do Raça");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/breed.json"));

		LOGGER.info("Atualizando Json com dados do Raça");
		jsonObject.put("porte", "1");

		LOGGER.info("Atualizando a Raça");
		mockMvcBreed.perform(put(ServicePath.BREED_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toJSONString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Teste PUT efetuado  com sucesso");
	}

	@Test
	public void test12() throws Exception {
		LOGGER.info("Inicio teste PUT Service");

		LOGGER.info("Buscando Json com dados do Serviço");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/service.json"));

		LOGGER.info("Atualizando Json com dados do Serviço");
		jsonObject.put("name", "Banho com tosa");
		jsonObject.put("size", "1");

		LOGGER.info("Atualizando a Raça");
		mockMvcService.perform(put(ServicePath.SERVICES_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toJSONString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Teste PUT efetuado  com sucesso");
	}

	@Test
	public void test13() throws Exception {
		LOGGER.info("Inicio teste GET DogLove");

		LOGGER.info("Buscando todas as raças");

		mockMvcPet.perform(get(ServicePath.PET_PATH + "/getPetSexoRaca?sex=femea&breed=1").accept(APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8));

		LOGGER.info("Teste efetuado GET sucesso");
	}

	@Test
	@Transactional
	@Rollback
	public void test14() throws Exception {
		LOGGER.info("Inicio teste POST Agendamento");

		LOGGER.info("Buscando Json com dados do Agendamento");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/agendamento.json"));

		LOGGER.info("Inserindo Agendamento");

		mockMvcAgendamento.perform(post(ServicePath.AGENDA_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Fim teste POST Agendamento");

	}

	@Test
	public void test15() throws Exception {
		LOGGER.info("Inicio teste POST Serviços");

		LOGGER.info("Buscando Json com dados do serviços");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/service.json"));

		LOGGER.info("Inserindo Serviço");

		mockMvcService.perform(post(ServicePath.SERVICES_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Fim teste POST pet");

	}

	@Test
	public void test17() throws Exception {
		LOGGER.info("Inicio teste DELETE pet");

		LOGGER.info("Removendo pet com id 1");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/pet.json"));

		mockMvcPet.perform(delete(ServicePath.PET_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toJSONString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Teste DELETE efetuado com sucesso");
	}
	
	@Test
	public void test18() throws Exception {
		LOGGER.info("Inicio teste DELETE Usuário");

		LOGGER.info("Removendo usuario com id 1");

		mockMvcUsuario.perform(delete(ServicePath.USER_PATH + "/id/1").accept(APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
		LOGGER.info("Teste DELETE efetuado com sucesso");
	}

	@Test
	public void test19() throws Exception {
		LOGGER.info("Inicio teste DELETE Raças");

		LOGGER.info("Removendo pet");

		jsonObject = (JSONObject) parser.parse(new FileReader("project_files/teste/breed.json"));

		mockMvcBreed.perform(delete(ServicePath.BREED_PATH + "/").contentType(APPLICATION_JSON)
				.content(jsonObject.toJSONString().getBytes())).andDo(print()).andExpect(status().isOk());

		LOGGER.info("Teste DELETE efetuado com sucesso");
	}

	@Test
	public void test20() throws Exception {
		LOGGER.info("Inicio teste Envio de email");

		String text = "Teste envio de Email";

		mockMvcEmail.perform(
				get(ServicePath.EMAIL_PATH + "/sendMailDogLove?texto=" + text + "&email=dogsystemudi@gmail.com")
						.accept(APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

		LOGGER.info("Teste teste Envio de email efetuado com sucesso");
	}

}
