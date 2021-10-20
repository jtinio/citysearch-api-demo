package com.example.service.integration;

import com.example.DemoApplication;
import com.example.commons.SearchLikeFlag;
import com.example.model.City;
import com.example.model.dto.CitySearchResultDto;
import com.example.repository.CityRepository;
import com.example.service.CityService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityServiceIntegrationTest {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityService cityService;

    @Test
    public void testEmptySearchCityNameByPrefixReturnNoResult()
    {
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("", SearchLikeFlag.PREFIX);
        assertEquals(0, citySearchResultDto.getResult().size());
    }

    @Test
    public void testEmptySearchCityNameByContainsReturnNoResult()
    {
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("", SearchLikeFlag.CONTAINS);
        assertEquals(0, citySearchResultDto.getResult().size());
    }

    @Test
    public void testEmptySearchCityNameBySuffixReturnNoResult()
    {
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("", SearchLikeFlag.SUFFIX);
        assertEquals(0, citySearchResultDto.getResult().size());
    }

    @Test
    public void testSearchCityNameByPrefixReturnListOfCities()
    {
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("Los", SearchLikeFlag.PREFIX);
        assertEquals(22, citySearchResultDto.getResult().size());
    }

    @Test
    public void testSearchCityNameByContainsReturnListOfCities()
    {
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("Los", SearchLikeFlag.CONTAINS);
        assertEquals(42, citySearchResultDto.getResult().size());
    }

    @Test
    public void testSearchCityNameBySuffixReturnListOfCities()
    {
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("York", SearchLikeFlag.SUFFIX);
        assertEquals(5, citySearchResultDto.getResult().size());
    }
}
