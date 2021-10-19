package com.example.controller.unit;

import com.example.commons.SearchLikeFlag;
import com.example.controller.CityController;
import com.example.model.City;
import com.example.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerUnitTest {

    @MockBean
    private CityService cityService;
    @Autowired
    private MockMvc mockMvc;

    private final City LA_CITY = new City("Los Angeles");
    private final City NEW_YORK_CITY = new City("New York");

    @Test
    public void PostSearchCityNameWithInvalidFlag() throws Exception{
        //act
        mockMvc.perform(get("/city/search?name=Los&flag=TEST"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        //assert
        verify(this.cityService, times(0))
                .getCitiesByName(anyString(), any());
    }

    @Test
    public void PostSearchCityNameWithEmptyName() throws Exception{
        //arrange
        when(this.cityService.getCitiesByName("", SearchLikeFlag.CONTAINS))
                .thenReturn(Collections.EMPTY_LIST);

        //act
        mockMvc.perform(get("/city/search"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        org.hamcrest.Matchers.hasSize(0)))
                .andDo(MockMvcResultHandlers.print());

        //assert
        verify(this.cityService, times(1))
                .getCitiesByName(anyString(), any());
    }

    @Test
    public void PostSearchCityNameByPrefix() throws Exception{
        //arrange
        when(this.cityService.getCitiesByName("Los", SearchLikeFlag.PREFIX))
                .thenReturn(Arrays.asList(LA_CITY));

        //act
        mockMvc.perform(get("/city/search?name=Los&flag=PREFIX"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //assert
        verify(this.cityService, times(1))
                .getCitiesByName(anyString(), any());
    }

    @Test
    public void PostSearchCityNameByContains() throws Exception{
        //arrange
        when(this.cityService.getCitiesByName("Ange", SearchLikeFlag.CONTAINS))
                .thenReturn(Arrays.asList(LA_CITY));

        //act
        mockMvc.perform(get("/city/search?name=Ange"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //assert
        verify(this.cityService, times(1))
                .getCitiesByName(anyString(), any());
    }

    @Test
    public void PostSearchCityNameBySuffix() throws Exception{
        //arrange
        when(this.cityService.getCitiesByName("York", SearchLikeFlag.SUFFIX))
                .thenReturn(Arrays.asList(NEW_YORK_CITY));

        //act
        mockMvc.perform(get("/city/search?name=York&flag=SUFFIX"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //assert
        verify(this.cityService, times(1))
                .getCitiesByName(anyString(), any());
    }
}
