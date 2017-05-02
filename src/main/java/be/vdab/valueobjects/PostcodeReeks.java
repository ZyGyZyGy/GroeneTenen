package be.vdab.valueobjects;

public class PostcodeReeks {

    private final static int MIN_POSTCODE = 1000; 
    private final static int MAX_POSTCODE = 9999; 
    private Integer vanPostcode;
    private Integer totPostcode;

    public Integer getVanPostcode() {
	return vanPostcode;
    }

    public void setVanPostcode(Integer vanPostcode) {
	valideer(vanPostcode);
	this.vanPostcode = vanPostcode;
    }

    public Integer getTotPostcode() {
	return totPostcode;
    }

    public void setTotPostcode(Integer totPostcode) {
	valideer(totPostcode);
	this.totPostcode = totPostcode;
    }

    private void valideer(int postcode) {
	if (postcode < MIN_POSTCODE || postcode > MAX_POSTCODE) {
	    throw new IllegalArgumentException();
	}
    }
    
    public boolean bevat(int postcode) {
	// bevat de reeks een bepaalde postcode ? (gebruikt in de repository layer)
	return postcode >= vanPostcode && postcode <= totPostcode;
    }

}