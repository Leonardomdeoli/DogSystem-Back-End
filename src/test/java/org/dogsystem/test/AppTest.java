
package org.dogsystem.test;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.dogsystem.controller.UserController;
import org.dogsystem.utils.ServicePath;
import org.json.JSONException;
import org.json.JSONObject;
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

	private static final Logger LOGGER = LogManager.getLogger(AppTest.class);

	@Autowired
	private UserController usuarioController;

	private MockMvc mockMvc;

	@Before
	public void init() throws IOException {
		mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).setControllerAdvice(new Exception()).build();
	}

	@Test
	public void salvandoUsuarioOk() throws Exception {
		mockMvc.perform(post(ServicePath.USER_PATH + "/").contentType(APPLICATION_JSON).content(buildJsonAsByte(false)))
				.andDo(print()).andExpect(status().isOk());

	}
	//
	// @Test
	// public void buscandoUsuarioOk() throws Exception {
	// mockMvc.perform(get(ServicePath.USER_PATH +
	// "/id/10").accept(APPLICATION_JSON)).andDo(print())
	// .andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
	// .andExpect(jsonPath("nome", is("leonardo"))).andExpect(jsonPath("email",
	// is("leonardo@gmail.com")));
	// }
	//
	// @Test
	// public void atualizandoUsuarioOk() throws Exception {
	// mockMvc.perform(put(ServicePath.USER_PATH +
	// "/").contentType(APPLICATION_JSON).content(buildJsonAsByte(true)))
	// .andDo(print()).andExpect(status().isOk());
	//
	// }
	//
	// @Test
	// public void atualizandoUsuarioBadRequest() throws Exception {
	// mockMvc.perform(put(ServicePath.USER_PATH +
	// "/").contentType(APPLICATION_JSON)
	// .content(new
	// JSONObject().toString().getBytes())).andDo(print()).andExpect(status().isBadRequest());
	//
	// }
	//
	// @Test
	// public void printInfo() throws Exception {
	// mockMvc.perform(get(ServicePath.USER_PATH + "/id/1")).andDo(print());
	//
	// }
	//
	// @Test
	// public void removendoUsuarioOk() throws Exception {
	// mockMvc.perform(delete(ServicePath.USER_PATH +
	// "/id/10").accept(APPLICATION_JSON)).andDo(print())
	// .andExpect(status().isOk());
	// }
	//
	// @Test
	// public void removendoUsuarioBadRequest() throws Exception {
	// mockMvc.perform(delete(ServicePath.USER_PATH +
	// "/id/67").accept(APPLICATION_JSON)).andDo(print())
	// .andExpect(status().isBadRequest());
	// }

	private byte[] buildJsonAsByte(boolean isUpdate) throws JSONException {

		JSONObject uf = new JSONObject();
		uf.put("sigla", "MG");

		JSONObject city = new JSONObject();
		city.put("name", "Uberlândia");
		city.put("uf", uf);

		JSONObject neighborhood = new JSONObject();
		neighborhood.put("name", "Jardim Europa");
		neighborhood.put("city", city);

		JSONObject addressentity = new JSONObject();
		addressentity.put("zipcode", "38410-250");
		addressentity.put("name", "Rua China");
		addressentity.put("neighborhood", neighborhood);

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", "leonardo");
		jsonObject.put("cpf", "07845296120");
		jsonObject.put("phone", "99143829");
		jsonObject.put("email", "leonardo2@gmail.com");
		jsonObject.put("password", "12121313");
		jsonObject.put("number", 121);
		jsonObject.put("address", addressentity);

		return jsonObject.toString().getBytes();
	}
}
