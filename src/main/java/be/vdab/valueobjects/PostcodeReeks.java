package be.vdab.valueobjects;

public class PostcodeReeks {

    private int vanPostcode;
    private int totPostcode;

    public int getVanpostcode() {
	return vanPostcode;
    }

    public void setVanpostcode(int vanPostcode) {
	this.vanPostcode = vanPostcode;
    }

    public int getTotpostcode() {
	return totPostcode;
    }

    public void setTotpostcode(int totPostcode) {
	this.totPostcode = totPostcode;
    }

    public boolean bevat(int postcode) {
	// bevat de reeks een bepaalde postcode ? (gebruikt in de repository layer)
	return postcode >= vanPostcode && postcode <= totPostcode;
    }

}