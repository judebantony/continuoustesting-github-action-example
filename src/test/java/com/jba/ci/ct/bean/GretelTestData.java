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
    
    @CsvBindByPosition(position = 5)
    private Boolean ashneer;
    
    @CsvBindByPosition(position = 6)
    private Boolean namita;
    
    @CsvBindByPosition(position = 7)
    private Boolean anupam;
    
    @CsvBindByPosition(position = 8)
    private Boolean vineeta;
    
    @CsvBindByPosition(position = 9)
    private Boolean aman;
    
    @CsvBindByPosition(position = 10)
    private Boolean peyush;
 
    @CsvBindByPosition(position = 11)
    private Boolean ghazal;
    
    
}
