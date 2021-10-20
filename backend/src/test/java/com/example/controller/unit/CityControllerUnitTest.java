package com.example.controller.unit;

import com.example.commons.SearchLikeFlag;
import com.example.controller.CityController;
import com.example.model.City;
import com.example.model.dto.CitySearchResultDto;
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
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerUnitTest {

    @MockBean
    private CityService cityService;
    @Autowired
    private MockMvc mockMvc;

    private final List<String> LA_CITIES = Arrays.asList("Los Angeles");
    private final List<String> NEW_YORK_CITIES = Arrays.asList("New York");

    @Test
    public void testSearchCityNameWithInvalidFlag() throws Exception{
        //act
        mockMvc.perform(get("/city/search?name=Los&flag=TEST"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        //assert
        verify(this.cityService, times(0))
                .getCitiesByName(anyString(), any());
    }

    @Test
    public void testSearchCityNameWithEmptyName() throws Exception{
        //arrange
        when(this.cityService.getCitiesByName("", SearchLikeFlag.CONTAINS))
                .thenReturn(new CitySearchResultDto(Collections.emptyList()));

        //act
        mockMvc.perform(get("/city/search"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result",
                        org.hamcrest.Matchers.hasSize(0)))
                .andDo(MockMvcResultHandlers.print());

        //assert
        verify(this.cityService, times(1))
                .getCitiesByName(anyString(), any());
    }

    @Test
    public void testSearchCityNameByPrefix() throws Exception{
        //arrange
        when(this.cityService.getCitiesByName("Los", SearchLikeFlag.PREFIX))
                .thenReturn(new CitySearchResultDto(LA_CITIES));

        //act
        mockMvc.perform(get("/city/search?name=Los&flag=PREFIX"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //assert
        verify(this.cityService, times(1))
                .getCitiesByName(anyString(), any());
    }

    @Test
    public void testSearchCityNameByContains() throws Exception{
        //arrange
        when(this.cityService.getCitiesByName("Ange", SearchLikeFlag.CONTAINS))
                .thenReturn(new CitySearchResultDto(LA_CITIES));

        //act
        mockMvc.perform(get("/city/search?name=Ange"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //assert
        verify(this.cityService, times(1))
                .getCitiesByName(anyString(), any());
    }

    @Test
    public void testSearchCityNameBySuffix() throws Exception{
        //arrange
        when(this.cityService.getCitiesByName("York", SearchLikeFlag.SUFFIX))
                .thenReturn(new CitySearchResultDto(NEW_YORK_CITIES));

        //act
        mockMvc.perform(get("/city/search?name=York&flag=SUFFIX"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //assert
        verify(this.cityService, times(1))
                .getCitiesByName(anyString(), any());
    }
}
