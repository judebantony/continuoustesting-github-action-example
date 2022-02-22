package com.jba.ci.ct.bean;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;
@Data
public class GretelTestData {
	@CsvBindByPosition(position = 0)
    private Integer episode;

    @CsvBindByPosition(position = 1)
    private Integer pitch_no;
    
    @CsvBindByPosition(position = 2)
    private String company;
    
    @CsvBindByPosition(position = 3)
    private String idea;
    
    @CsvBindByPosition(position = 4)
    private String deal;
    
    
}
