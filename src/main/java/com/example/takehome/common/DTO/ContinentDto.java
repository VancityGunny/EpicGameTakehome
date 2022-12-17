package com.example.takehome.common.DTO;

import lombok.Getter;

@Getter
        public class ContinentDto {
            private String code;
            private String name;
            private CountryDto[] countries;
        }