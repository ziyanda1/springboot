package com.eohdigital;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.eohdigital.domain.Cic;
import com.eohdigital.repository.CicRepository;
import com.eohdigital.web.dto.CicDto;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@WebAppConfiguration
public class BootApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private CicRepository cicRepository;

	private MockMvc mockMvc;

	private Cic cic;

	private HttpMessageConverter httpMessageConverter;

	@Test
	public void testFindCic() throws Exception{
		mockMvc.perform(get("/cic/" + this.cic.getCicId()))
				.andExpect(status().isOk());
	}

	@Test
	public void testSaveCic() throws Exception{

		CicDto cicDto = new CicDto();
		cicDto.setCicId(1998L);
		cicDto.setCicType("Cic Type Test");
		cicDto.setSubject("Cic Subject Test");
		cicDto.setBody("Cic Body Test");

		mockMvc.perform(post("/cic/")
				.content(toJson(cicDto))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		this.cicRepository.deleteAllInBatch();

		Cic cic = new Cic();
		cic.setCicId(1997L);
		cic.setCicType("Cic Type");
		cic.setSubject("Cic Subject");
		cic.setBody("Cic Body");

		this.cic = cicRepository.save(cic);
	}

	protected String toJson(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		httpMessageConverter.write(
				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}


	@Autowired
	void loadConverters(HttpMessageConverter<?>[] converters) {
		this.httpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);
	}
}
