package com.example.takehome;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ContextIdApplicationContextInitializer;
import org.springframework.web.bind.annotation.RestController;

import com.example.takehome.common.CountriesBLHelper;
import com.example.takehome.common.CountryClient;
import com.example.takehome.common.DTO.ContinentDto;
import com.example.takehome.common.DTO.ContinentNeighborsDto;
import com.example.takehome.common.DTO.ContinentQueryResultDto;
import com.example.takehome.common.DTO.ContinentsQueryResultDto;
import com.example.takehome.common.DTO.CountriesQueryResultDto;
import com.example.takehome.common.DTO.CountryDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Slf4j
@SpringBootApplication
@RestController
public class TakehomeApplication {

	public static void main(String[] args) {
		log.debug("Starting the API...");
		SpringApplication.run(TakehomeApplication.class, args);	
	}

	@GetMapping("/findContinentNeighbors/{inputCountryCodes}")
	public ResponseEntity<Object> getNeighborCountries(@PathVariable String[] inputCountryCodes) {
		log.debug("getNeighborCountries endpoint: called.");
		// List<MatchedContinentResult> result = CountriesBLHelper.findCountriesInSameContinent(inputCountryCodes);
		CountryClient client = new CountryClient("https://countries.trevorblades.com/");
		try{
			// get detail of given countries
			CountriesQueryResultDto countriesResult = client.getCountries(inputCountryCodes);
			log.debug("found countries"+countriesResult.toString());
			// now get continents code from these countries
			List<CountryDto> countriesList = Arrays.asList(countriesResult.getData().getCountries());
			// get distinct continents from that
			List<String> distinctContinents = countriesList.stream().map(e -> e.getContinent().getCode()).distinct().collect(Collectors.toList());;
			// now get these continents
			ContinentsQueryResultDto continentsResult = client.getContinents(distinctContinents.toArray(new String[0]));
			log.debug("found countries"+continentsResult.toString());
			
			// map result to final dto
			List<ContinentDto> continentsList = Arrays.asList(continentsResult.getData().getContinents());

			// now display these continents back but subtract the initial countries from the list
			List<ContinentNeighborsDto> responseObject = CountriesBLHelper.prepContinentNeighborsResult(continentsList, countriesList);
			return new ResponseEntity<Object>(responseObject, HttpStatus.OK);			
		}
		catch(Exception e){
			log.error("there is an error findContinentNeighbors:"+e.toString(), e);			
			return new ResponseEntity<Object>("There is an error findContinentNeighbors", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	
}
