package com.example.controller;

import com.example.commons.SearchLikeFlag;
import com.example.commons.exceptions.InvalidRestRequestException;
import com.example.model.dto.CitySearchResultDto;
import com.example.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api("Endpoints for Searching Cities.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @ApiOperation(value = "Find City by name", notes = "Name search by flags [PREFIX(string%), SUFFIX(%string) or CONTAINS(%string%)]")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response=List.class ),
            @ApiResponse(code = 400, message = "Invalid flag value" )
    })
    @GetMapping("/search")
    public CitySearchResultDto searchCity(final @RequestParam(name ="flag", required = false, defaultValue = "CONTAINS") String flag,
                                          final @RequestParam(name ="name", defaultValue = "") String name) {
        try {
            return this.cityService.getCitiesByName(name, SearchLikeFlag.valueOf(flag));
        } catch(Exception exception) {
            log.error("Invalid flag value. Exception Message: {}", exception.getMessage());
            throw new InvalidRestRequestException("Invalid flag value");
        }
    }
}
