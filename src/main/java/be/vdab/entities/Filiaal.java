package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import be.vdab.valueobjects.Adres;

public class Filiaal implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String naam;
    private boolean hoofdFiliaal;
    private BigDecimal waardeGebouw;
    private LocalDate ingebruikname;
    private Adres adres;

    public Filiaal(String naam, boolean hoofdFiliaal, BigDecimal waardeGebouw, 
	    LocalDate ingebruikname, Adres adres) {
	this.naam = naam;
	this.hoofdFiliaal = hoofdFiliaal;
	this.waardeGebouw = waardeGebouw;
	this.ingebruikname = ingebruikname;
	this.adres = adres;
    }

    public Filiaal(long id, String naam, boolean hoofdFiliaal, BigDecimal waardeGebouw, 
	    LocalDate ingebruikname, Adres adres) {
	this(naam, hoofdFiliaal, waardeGebouw, ingebruikname, adres);
	this.id = id;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getNaam() {
	return naam;
    }

    public void setNaam(String naam) {
	this.naam = naam;
    }

    public boolean isHoofdFiliaal() {
	return hoofdFiliaal;
    }

    public void setHoofdFiliaal(boolean hoofdFiliaal) {
	this.hoofdFiliaal = hoofdFiliaal;
    }

    public BigDecimal getWaardeGebouw() {
	return waardeGebouw;
    }

    public void setWaardeGebouw(BigDecimal waardeGebouw) {
	this.waardeGebouw = waardeGebouw;
    }

    public LocalDate getIngebruikname() {
	return ingebruikname;
    }

    public void setIngebruikname(LocalDate ingebruikname) {
	this.ingebruikname = ingebruikname;
    }

    public Adres getAdres() {
	return adres;
    }

    public void setAdres(Adres adres) {
	this.adres = adres;
    }

}
