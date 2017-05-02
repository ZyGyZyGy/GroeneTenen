package be.vdab.valueobjects;

import javax.validation.constraints.NotNull;

import be.vdab.constraints.Postcode;
import be.vdab.constraints.PostcodeReeksVanKleinerDanOfGelijkAanTot;

@PostcodeReeksVanKleinerDanOfGelijkAanTot
public class PostcodeReeks {

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