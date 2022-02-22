package com.jba.ci.ct.bean;

import com.opencsv.bean.CsvBindByPosition;

public class GretelTestData {
	@CsvBindByPosition(position = 0)
    private String episode;

    @CsvBindByPosition(position = 1)
    private String pitch_no;
    
    @CsvBindByPosition(position = 2)
    private String idea;
    
    @CsvBindByPosition(position = 3)
    private String deal;
}
