package vj.stock.org.stock;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class StockResourceTest {

	@Mock
	private StockRepository repository;
	@InjectMocks
	StockResource stockResource;

	@Test
	public void createStock() throws Exception {
		Stock s = new Stock();
		Mockito.when(repository.save(Mockito.any())).thenReturn(s);
		MockMvcBuilders.standaloneSetup(stockResource).build().perform(post("/stocks")

				.contentType("application/json").content("{}").accept("application/json")).andDo(print())
				.andExpect(status().isCreated());

	}

	@Test
	public void getStock() throws Exception {
		Stock s = new Stock("VMware", 1, 1);
		Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(s));
		String resp = MockMvcBuilders.standaloneSetup(stockResource).build().perform(get("/stocks")

				.accept("application/json")).andReturn().getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		Stock[] stocks = mapper.readValue(resp, Stock[].class);
		assertTrue(stocks.length > 0);
		assertEquals(stocks[0], s);
	}

}
