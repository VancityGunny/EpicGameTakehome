package com.example.takehome.common.DTO;

import lombok.Getter;

@Getter
    public class CountryDto {

      private String name;
      private String capital;
      private String currency;
      private ContinentDto continent;
    }