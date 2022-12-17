package com.example.takehome.common.DTO;

import lombok.Getter;

@Getter
    public class CountryDto {

      private String code;
      private String name;
      private ContinentDto continent;
    }