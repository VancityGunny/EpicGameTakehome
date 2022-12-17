package com.example.takehome.common.DTO;

import lombok.Getter;

@Getter
public class CountriesQueryResultDto {

    private CountriesData data;

    @Getter
    public class CountriesData {
        
        private CountryDto[] countries;
    }
}
