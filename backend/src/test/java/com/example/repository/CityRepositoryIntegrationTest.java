package com.example.repository;

import com.example.model.City;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CityRepositoryIntegrationTest extends BaseRepositoryTest{

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testStartsWithNoResult()
    {
        List<City> results = cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc("1");
        assertEquals(0, results.size());
    }

    @Test
    public void testContainsNoResult()
    {
        List<City> results = cityRepository.findByNameContainsIgnoreCaseOrderByNameAsc("1");
        assertEquals(0, results.size());
    }

    @Test
    public void testEndsWithNoResult()
    {
        List<City> results = cityRepository.findByNameEndsWithIgnoreCaseOrderByNameAsc("1");
        assertEquals(0, results.size());
    }

    @Test
    public void testStartsWith()
    {
        List<City> results = cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc("Los");
        assertEquals(27, results.size());
    }

    @Test
    public void testContains()
    {
        List<City> results = cityRepository.findByNameContainsIgnoreCaseOrderByNameAsc("Los");
        assertEquals(49, results.size());
    }

    @Test
    public void testEndsWith()
    {
        List<City> results = cityRepository.findByNameEndsWithIgnoreCaseOrderByNameAsc("York");
        assertEquals(14, results.size());
    }

}
