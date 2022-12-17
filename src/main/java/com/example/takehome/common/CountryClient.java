package com.example.takehome.common;

import com.example.takehome.common.GraphqlRequestBody;
import com.example.takehome.common.GraphqlSchemaReaderUtil;
import com.example.takehome.common.DTO.ContinentQueryResultDto;
import com.example.takehome.common.DTO.ContinentsQueryResultDto;
import com.example.takehome.common.DTO.CountriesQueryResultDto;
import com.example.takehome.common.DTO.CountryQueryResultDto;

import java.io.IOException;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CountryClient {

  private final String url;

  public CountryClient(@Value("https://countries.trevorblades.com/") String url) {
    this.url = url;
  }

  public ContinentsQueryResultDto getContinents(final String[] continentCodes) throws IOException {

    WebClient webClient = WebClient.builder().build();

    GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

    final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("getContinents");
    final String variables = GraphqlSchemaReaderUtil.getSchemaFromFileName("variablesContinents");
    String continentArrayCode = "[\"" + String.join("\",\"",continentCodes) + "\"]";
    graphQLRequestBody.setQuery(query);
    graphQLRequestBody.setVariables(variables.replace("codeValue", continentArrayCode));
    
    return webClient.post()
        .uri(url)
        .bodyValue(graphQLRequestBody)
        .retrieve()
        .bodyToMono(ContinentsQueryResultDto.class)
        .block();
  }

  public CountriesQueryResultDto getCountries(final String[] countriesCodes) throws IOException {

    WebClient webClient = WebClient.builder().build();

    GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

    final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("getCountries");
    final String variables = GraphqlSchemaReaderUtil.getSchemaFromFileName("variablesCountries");
    String countryArrayCode = "[\"" + String.join("\",\"",countriesCodes) + "\"]";
    graphQLRequestBody.setQuery(query);
    graphQLRequestBody.setVariables(variables.replace("codeValue", countryArrayCode));
    
    return webClient.post()
        .uri(url)
        .bodyValue(graphQLRequestBody)
        .retrieve()
        .bodyToMono(CountriesQueryResultDto.class)
        .block();
  }
}
