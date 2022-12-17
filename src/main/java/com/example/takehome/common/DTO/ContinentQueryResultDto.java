package com.example.takehome.common.DTO;

import lombok.Getter;

@Getter
public class ContinentQueryResultDto {

    private ContinentData data;

    @Getter
    public class ContinentData {
        
        private ContinentDto continent;
    }
}
