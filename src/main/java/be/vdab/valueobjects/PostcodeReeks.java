package be.vdab.valueobjects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import be.vdab.constraints.Postcode;

public class PostcodeReeks {

    private final static int MIN_POSTCODE = 1000; 
    private final static int MAX_POSTCODE = 9999;
    
    @NotNull @Postcode
    private Integer vanPostcode;
    @NotNull @Postcode
    private Integer totPostcode;

    public Integer getVanPostcode() {
	return vanPostcode;
    }

    public void setVanPostcode(Integer vanPostcode) {
	this.vanPostcode = vanPostcode;
    }

    public Integer getTotPostcode() {
	return totPostcode;
    }

    public void setTotPostcode(Integer totPostcode) {
	this.totPostcode = totPostcode;
    }

    public boolean bevat(int postcode) {
	// bevat de reeks een bepaalde postcode ? (gebruikt in de repository layer)
	return postcode >= vanPostcode && postcode <= totPostcode;
    }

}