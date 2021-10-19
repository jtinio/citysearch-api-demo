package com.example.service;

import com.example.commons.SearchLikeFlag;
import com.example.model.City;
import com.example.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    @Override
    public List<City> getCitiesByName(final String name, SearchLikeFlag searchLikeFlag) {
        if(name == null || name.isBlank()) {
            return Collections.emptyList();
        }
        switch (searchLikeFlag) {
            case PREFIX:
                return this.cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc(name);
            case CONTAINS:
                return this.cityRepository.findByNameContainsIgnoreCaseOrderByNameAsc(name);
            default:
                return this.cityRepository.findByNameEndsWithIgnoreCaseOrderByNameAsc(name);
        }
    }

}
