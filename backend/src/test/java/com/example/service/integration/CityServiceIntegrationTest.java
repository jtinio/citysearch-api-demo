package com.example.service.integration;

import com.example.DemoApplication;
import com.example.commons.SearchLikeFlag;
import com.example.model.City;
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
        List<City> results = this.cityService.getCitiesByName("", SearchLikeFlag.PREFIX);
        assertEquals(0, results.size());
    }

    @Test
    public void testEmptySearchCityNameByContainsReturnNoResult()
    {
        List<City> results = this.cityService.getCitiesByName("", SearchLikeFlag.CONTAINS);
        assertEquals(0, results.size());
    }

    @Test
    public void testEmptySearchCityNameBySuffixReturnNoResult()
    {
        List<City> results = this.cityService.getCitiesByName("", SearchLikeFlag.SUFFIX);
        assertEquals(0, results.size());
    }

    @Test
    public void testSearchCityNameByPrefixReturnListOfCities()
    {
        List<City> results = this.cityService.getCitiesByName("Los", SearchLikeFlag.PREFIX);
        assertEquals(27, results.size());
    }

    @Test
    public void testSearchCityNameByContainsReturnListOfCities()
    {
        List<City> results = this.cityService.getCitiesByName("Los", SearchLikeFlag.CONTAINS);
        assertEquals(49, results.size());
    }

    @Test
    public void testSearchCityNameBySuffixReturnListOfCities()
    {
        List<City> results = this.cityService.getCitiesByName("York", SearchLikeFlag.SUFFIX);
        assertEquals(14, results.size());
    }
}
