package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.vdab.restservices.LocalDateAdapter;
import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "filialen")
@XmlRootElement
public class Filiaal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank
    @Length(min = 1, max = 50)
    @SafeHtml
    private String naam;
    
    private boolean hoofdFiliaal;
    
    @NumberFormat(style = Style.CURRENCY)
    @NotNull 
    @Min(0) 
    @Digits(integer = 10, fraction = 2) 
    private BigDecimal waardeGebouw;
    
    @DateTimeFormat(style = "S-")
    private LocalDate ingebruikname;
    
    @Embedded
    @Valid
    private Adres adres;
    
    @OneToMany(mappedBy = "filiaal")
    @XmlTransient
    @JsonIgnore
    private Set<Werknemer> werknemers;
    
    @Version 
    private long versie; 

    public Set<Werknemer> getWerknemers() {
	return Collections.unmodifiableSet(werknemers);
    }
    
    public Filiaal() {
	
    }

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

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class) 
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
    
    public void afschrijven() {
	this.waardeGebouw = BigDecimal.ZERO;
    }

}
