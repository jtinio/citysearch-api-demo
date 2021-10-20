package com.example.service.unit;

import com.example.commons.SearchLikeFlag;
import com.example.model.City;
import com.example.model.dto.CitySearchResultDto;
import com.example.repository.CityRepository;
import com.example.service.CityService;
import com.example.service.CityServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CityServiceUnitTest {

    private CityRepository cityRepository;
    private CityService cityService;

    private final List<String> LA_CITIES = Arrays.asList("Los Angeles");
    private final List<String> NEW_YORK_CITIES = Arrays.asList("New York");

    @Before
    public void setUp(){
        this.cityRepository = mock(CityRepository.class);
        this.cityService = new CityServiceImpl(this.cityRepository);
    }

    @Test
    public void givenEmptySearchCityNameByPrefixReturnNoResult(){
        //arrange
        when(this.cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Collections.emptyList());

        //act
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("", SearchLikeFlag.PREFIX);

        //assert
        assertEquals(0, citySearchResultDto.getResult().size());
        verify(this.cityRepository, never())
                .findByNameStartsWithIgnoreCaseOrderByNameAsc(any());
        verify(this.cityRepository, never())
                .findByNameContainsIgnoreCaseOrderByNameAsc(any());
        verify(this.cityRepository, never())
                .findByNameStartsWithIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenEmptySearchCityNameByContainsReturnNoResult(){
        //arrange
        when(this.cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Collections.emptyList());

        //act
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("", SearchLikeFlag.CONTAINS);

        //assert
        assertEquals(0, citySearchResultDto.getResult().size());
        verify(this.cityRepository, never())
                .findByNameStartsWithIgnoreCaseOrderByNameAsc(any());
        verify(this.cityRepository, never())
                .findByNameContainsIgnoreCaseOrderByNameAsc(any());
        verify(this.cityRepository, never())
                .findByNameStartsWithIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenEmptySearchCityNameBySuffixReturnNoResult(){
        //arrange
        when(this.cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Collections.emptyList());

        //act
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("", SearchLikeFlag.SUFFIX);

        //assert
        assertEquals(0, citySearchResultDto.getResult().size());
        verify(this.cityRepository, never())
                .findByNameStartsWithIgnoreCaseOrderByNameAsc(any());
        verify(this.cityRepository, never())
                .findByNameContainsIgnoreCaseOrderByNameAsc(any());
        verify(this.cityRepository, never())
                .findByNameStartsWithIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenInvalidSearchCityNameByPrefixReturnNoResult(){
        //arrange
        when(this.cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Collections.emptyList());

        //act
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("Q", SearchLikeFlag.PREFIX);

        //assert
        assertEquals(0, citySearchResultDto.getResult().size());
        verify(this.cityRepository, times(1))
                .findByNameStartsWithIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenInvalidSearchCityNameByContainsReturnNoResult(){
        //arrange
        when(this.cityRepository.findByNameContainsIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Collections.emptyList());

        //act
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("Q", SearchLikeFlag.CONTAINS);

        //assert
        assertEquals(0, citySearchResultDto.getResult().size());
        verify(this.cityRepository, times(1))
                .findByNameContainsIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenInvalidSearchCityNameBySuffixReturnNoResult(){
        //arrange
        when(this.cityRepository.findByNameEndsWithIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Collections.emptyList());

        //act
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("Q", SearchLikeFlag.SUFFIX);

        //assert
        assertEquals(0, citySearchResultDto.getResult().size());
        verify(this.cityRepository, times(1))
                .findByNameEndsWithIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenSearchCityNameByPrefixReturnListOfCities(){
        //arrange
        when(this.cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(LA_CITIES);

        //act
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("Los", SearchLikeFlag.PREFIX);

        //assert
        assertEquals(1, citySearchResultDto.getResult().size());
        assertEquals(citySearchResultDto.getResult().get(0), LA_CITIES.get(0));
        verify(this.cityRepository, times(1))
                .findByNameStartsWithIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenSearchCityNameByContainsReturnListOfCities(){
        //arrange
        when(this.cityRepository.findByNameContainsIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(LA_CITIES);

        //act
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("Ange", SearchLikeFlag.CONTAINS);

        //assert
        assertEquals(1, citySearchResultDto.getResult().size());
        assertEquals(citySearchResultDto.getResult().get(0), LA_CITIES.get(0));
        verify(this.cityRepository, times(1))
                .findByNameContainsIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenSearchCityNameBySuffixReturnListOfCities(){
        //arrange
        when(this.cityRepository.findByNameEndsWithIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(NEW_YORK_CITIES);

        //act
        final CitySearchResultDto citySearchResultDto = this.cityService.getCitiesByName("York", SearchLikeFlag.SUFFIX);

        //assert
        assertEquals(1, citySearchResultDto.getResult().size());
        assertEquals(citySearchResultDto.getResult().get(0), NEW_YORK_CITIES.get(0));
        verify(this.cityRepository, times(1))
                .findByNameEndsWithIgnoreCaseOrderByNameAsc(any());
    }

}
