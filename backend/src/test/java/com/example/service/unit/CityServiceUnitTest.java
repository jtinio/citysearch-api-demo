package com.example.service.unit;

import com.example.commons.SearchLikeFlag;
import com.example.model.City;
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

    private final City LA_CITY = new City("Los Angeles");
    private final City NEW_YORK_CITY = new City("New York");

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
        List<City> cityList = this.cityService.getCitiesByName("", SearchLikeFlag.PREFIX);

        //assert
        assertEquals(0, cityList.size());
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
        List<City> cityList = this.cityService.getCitiesByName("", SearchLikeFlag.CONTAINS);

        //assert
        assertEquals(0, cityList.size());
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
        List<City> cityList = this.cityService.getCitiesByName("", SearchLikeFlag.SUFFIX);

        //assert
        assertEquals(0, cityList.size());
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
        List<City> cityList = this.cityService.getCitiesByName("Q", SearchLikeFlag.PREFIX);

        //assert
        assertEquals(0, cityList.size());
        verify(this.cityRepository, times(1))
                .findByNameStartsWithIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenInvalidSearchCityNameByContainsReturnNoResult(){
        //arrange
        when(this.cityRepository.findByNameContainsIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Collections.emptyList());

        //act
        List<City> cityList = this.cityService.getCitiesByName("Q", SearchLikeFlag.CONTAINS);

        //assert
        assertEquals(0, cityList.size());
        verify(this.cityRepository, times(1))
                .findByNameContainsIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenInvalidSearchCityNameBySuffixReturnNoResult(){
        //arrange
        when(this.cityRepository.findByNameEndsWithIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Collections.emptyList());

        //act
        List<City> cityList = this.cityService.getCitiesByName("Q", SearchLikeFlag.SUFFIX);

        //assert
        assertEquals(0, cityList.size());
        verify(this.cityRepository, times(1))
                .findByNameEndsWithIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenSearchCityNameByPrefixReturnListOfCities(){
        //arrange
        when(this.cityRepository.findByNameStartsWithIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Arrays.asList(LA_CITY));

        //act
        List<City> cityList = this.cityService.getCitiesByName("Los", SearchLikeFlag.PREFIX);

        //assert
        assertEquals(1, cityList.size());
        assertEquals(cityList.get(0), LA_CITY);
        verify(this.cityRepository, times(1))
                .findByNameStartsWithIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenSearchCityNameByContainsReturnListOfCities(){
        //arrange
        when(this.cityRepository.findByNameContainsIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Arrays.asList(LA_CITY));

        //act
        List<City> cityList = this.cityService.getCitiesByName("Ange", SearchLikeFlag.CONTAINS);

        //assert
        assertEquals(1, cityList.size());
        assertEquals(cityList.get(0), LA_CITY);
        verify(this.cityRepository, times(1))
                .findByNameContainsIgnoreCaseOrderByNameAsc(any());
    }

    @Test
    public void givenSearchCityNameBySuffixReturnListOfCities(){
        //arrange
        when(this.cityRepository.findByNameEndsWithIgnoreCaseOrderByNameAsc(any()))
                .thenReturn(Arrays.asList(NEW_YORK_CITY));

        //act
        List<City> cityList = this.cityService.getCitiesByName("York", SearchLikeFlag.SUFFIX);

        //assert
        assertEquals(1, cityList.size());
        assertEquals(cityList.get(0), NEW_YORK_CITY);
        verify(this.cityRepository, times(1))
                .findByNameEndsWithIgnoreCaseOrderByNameAsc(any());
    }

}
