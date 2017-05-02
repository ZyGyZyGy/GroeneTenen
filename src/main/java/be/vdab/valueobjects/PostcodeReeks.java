package be.vdab.valueobjects;

public class PostcodeReeks {

    private Integer vanPostcode;
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