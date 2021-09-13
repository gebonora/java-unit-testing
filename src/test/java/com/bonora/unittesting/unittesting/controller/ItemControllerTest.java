package com.bonora.unittesting.unittesting.controller;

import com.bonora.unittesting.unittesting.business.ItemBusinessService;
import com.bonora.unittesting.unittesting.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemBusinessService businessService;

    @Test
    public void dummyItem_basic() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}", result.getResponse().getContentAsString(), true);
    }

    @Test
    public void dummyItem_basicNotStrict() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals("{\"id\":1,\"name\":\"Ball\"}", result.getResponse().getContentAsString(), false);
    }

    @Test
    public void itemFromBusinessService_basic() throws Exception {
        when(businessService.retrieveHardcodedItem()).thenReturn(
                new Item(2, "Item2", 10, 10)
        );

        RequestBuilder request = MockMvcRequestBuilders.get("/item-from-business-service").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals("{id:2,name:Item2,price:10,quantity:10}", result.getResponse().getContentAsString(), true);
    }

}
