package com.example.takehome.common.DTO;

import lombok.Getter;

@Getter
public class CountryQueryResultDto {

  private CountryData data;

  @Getter
  public class CountryData {

    private CountryDto country;
    
  }
}

