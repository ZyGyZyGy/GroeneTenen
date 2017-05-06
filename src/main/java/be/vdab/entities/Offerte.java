package be.vdab.entities;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

public class Offerte implements Serializable {

    public interface Stap1 {
    }

    public interface Stap2 {
    }

    private static final long serialVersionUID = 1L;
    
    @NotBlank(groups = Stap1.class)
    @SafeHtml(groups = Stap1.class)
    private String voornaam;
    @NotBlank(groups = Stap1.class)
    @SafeHtml(groups = Stap1.class)
    private String familienaam;
    @NotBlank(groups = Stap1.class)
    @Email(groups = Stap1.class)
    private String emailAdres;
    @NotNull(groups = Stap2.class)
    @Min(value = 1, groups = Stap2.class)
    private Integer oppervlakte;
    
    public Offerte() {
	
    }

    public String getVoornaam() {
	return voornaam;
    }

    public void setVoornaam(String voornaam) {
	this.voornaam = voornaam;
    }

    public String getFamilienaam() {
	return familienaam;
    }

    public void setFamilienaam(String familienaam) {
	this.familienaam = familienaam;
    }

    public String getEmailAdres() {
	return emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
	this.emailAdres = emailAdres;
    }

    public Integer getOppervlakte() {
	return oppervlakte;
    }

    public void setOppervlakte(Integer oppervlakte) {
	this.oppervlakte = oppervlakte;
    }

}