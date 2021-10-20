package com.example.controller.integration;

import com.example.DemoApplication;
import com.example.model.dto.CitySearchResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCityNameSearchWithInvalidFlag() {
        ResponseEntity<CitySearchResultDto> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/api/city/search?flag=TEST", CitySearchResultDto.class);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testEmptyCityNameSearch() {
        ResponseEntity<CitySearchResultDto> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/api/city/search", CitySearchResultDto.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(0, responseEntity.getBody().getResult().size());
    }

    @Test
    public void testCityNameSearchByPrefixShouldReturnListOfResult() {
        ResponseEntity<CitySearchResultDto> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/api/city/search?flag=PREFIX&name=Los", CitySearchResultDto.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(22, responseEntity.getBody().getResult().size());
    }

    @Test
    public void testCityNameSearchByContainsShouldReturnListOfResult() {
        ResponseEntity<CitySearchResultDto> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/api/city/search?name=Los", CitySearchResultDto.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(42, responseEntity.getBody().getResult().size());
    }

    @Test
    public void testCityNameSearchBySuffixShouldReturnListOfResult() {
        ResponseEntity<CitySearchResultDto> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/api/city/search?flag=SUFFIX&name=York", CitySearchResultDto.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(5, responseEntity.getBody().getResult().size());
    }

}
