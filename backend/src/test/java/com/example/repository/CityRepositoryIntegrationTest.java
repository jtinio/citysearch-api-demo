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
        List<String> results = this.cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc("1");
        assertEquals(0, results.size());
    }

    @Test
    public void testContainsNoResult()
    {
        List<String> results = this.cityRepository.findByNameContainsIgnoreCaseOrderByNameAsc("1");
        assertEquals(0, results.size());
    }

    @Test
    public void testEndsWithNoResult()
    {
        List<String> results = this.cityRepository.findByNameEndsWithIgnoreCaseOrderByNameAsc("1");
        assertEquals(0, results.size());
    }

    @Test
    public void testStartsWith()
    {
        List<String> results = this.cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc("Los".toUpperCase());
        assertEquals(22, results.size());
    }

    @Test
    public void testContains()
    {
        List<String> results = this.cityRepository.findByNameContainsIgnoreCaseOrderByNameAsc("Los".toUpperCase());
        assertEquals(42, results.size());
    }

    @Test
    public void testEndsWith()
    {
        List<String> results = this.cityRepository.findByNameEndsWithIgnoreCaseOrderByNameAsc("York".toUpperCase());
        assertEquals(5, results.size());
    }

}
