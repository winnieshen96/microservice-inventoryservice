package com.microservice.project4.inventoryservice;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.microservice.project4.inventoryservice.controller.InventoryServiceController;
import com.microservice.project4.inventoryservice.domain.Good;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryServiceController.class)
public class InventoryServiceApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void givenShop_whenGetInventory_thenReturnList() throws Exception {
		List<Good> lst = new ArrayList<Good>();
        Good g1 = new Good("Apple", 2);
        lst.add(g1);
        Good g2 = new Good("Banana", 1);
        lst.add(g2);
		mvc.perform(get("/getInventoryForShop/fruitshop")
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$", hasSize(2)))
		        .andExpect(jsonPath("$[0].name", is(g1.getName())));
	}

}
