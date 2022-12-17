package com.example.takehome.common.DTO;

import lombok.Getter;

@Getter
public class ContinentsQueryResultDto {

    private ContinentsData data;

    @Getter
    public class ContinentsData {
        
        private ContinentDto[] continents;
    }
}
