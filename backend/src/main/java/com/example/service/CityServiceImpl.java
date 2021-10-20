package com.example.service;

import com.example.commons.SearchLikeFlag;
import com.example.model.dto.CitySearchResultDto;
import com.example.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService{

    private final CitySearchResultDto EMPTY_CITY_SEARCH_RESULT_DTO = new CitySearchResultDto(Collections.emptyList());

    private final CityRepository cityRepository;

    @Override
    public CitySearchResultDto getCitiesByName(final String name, SearchLikeFlag searchLikeFlag) {
        if(name == null || name.isBlank()) {
            return EMPTY_CITY_SEARCH_RESULT_DTO;
        }
        switch (searchLikeFlag) {
            case PREFIX:
                return new CitySearchResultDto(this.cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc(name.toUpperCase()));
            case CONTAINS:
                return new CitySearchResultDto(this.cityRepository.findByNameContainsIgnoreCaseOrderByNameAsc(name.toUpperCase()));
            default:
                return new CitySearchResultDto(this.cityRepository.findByNameEndsWithIgnoreCaseOrderByNameAsc(name.toUpperCase()));
        }
    }

}
