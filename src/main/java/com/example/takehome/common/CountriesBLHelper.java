package com.example.takehome.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.example.takehome.common.DTO.ContinentDto;
import com.example.takehome.common.DTO.ContinentNeighborsDto;
import com.example.takehome.common.DTO.CountryDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import java.util.stream.Collectors;

public class CountriesBLHelper {

    public static List<ContinentNeighborsDto> prepContinentNeighborsResult(List<ContinentDto> foundContinents, List<CountryDto> inputCountries){        
			List<String> arrayCountries = inputCountries.stream().map(e->e.getCode()).collect(Collectors.toList());
            // create Continent&InputCountries map			
			Map<String, List<String>> inputCountriesMap = inputCountries.stream().collect(Collectors.groupingBy(s->s.getContinent().getCode(), Collectors.mapping(CountryDto::getCode, Collectors.toList())));
        return foundContinents.stream().map(e -> new ContinentNeighborsDto(inputCountriesMap.get(e.getCode()).toArray(size -> new String[size]),e.getName(),Arrays.asList(e.getCountries()).stream().filter(tmpCountry -> !arrayCountries.contains(tmpCountry.getCode())).map(f -> f.getCode()).toArray(size -> new String[size]))).collect(Collectors.toList());			
    }
}
