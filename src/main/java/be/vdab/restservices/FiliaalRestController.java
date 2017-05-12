package be.vdab.restservices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalNietGevondenException;
import be.vdab.services.FiliaalService;

@RestController
@RequestMapping("/filialen")
class FiliaalRestController {
    
    private final FiliaalService filiaalService;

    FiliaalRestController(FiliaalService filiaalService) {
	this.filiaalService = filiaalService;
    }
 
    @GetMapping("{filiaal}")
    Filiaal read(@PathVariable Filiaal filiaal) {
	if (filiaal == null) {
	    throw new FiliaalNietGevondenException();
	}
	return filiaal;
    }
    
    @ExceptionHandler(FiliaalNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void filiaalNietGevonden() {
	
    }
    
}
