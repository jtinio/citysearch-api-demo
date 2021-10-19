package com.example.service;

import com.example.commons.SearchLikeFlag;
import com.example.model.City;

import java.util.List;

public interface CityService {

    /**
     * Retrieve city/cities by name
     * @param name the name of the city to search
     * @param searchLikeFlag {@link SearchLikeFlag}
     * @return List of {@link City}
     */
    List<City> getCitiesByName(final String name, SearchLikeFlag searchLikeFlag);
}
