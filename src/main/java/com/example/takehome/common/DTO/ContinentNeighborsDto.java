package com.example.takehome.common.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor 
public class ContinentNeighborsDto {        
    // containing country codes from input countrycodes that belong to this continent    
    private String[] countries;
    private String name;
    // containing country codes from the countries that reside on this same continent as an input countries
    private String[] otherCountries; 
}

