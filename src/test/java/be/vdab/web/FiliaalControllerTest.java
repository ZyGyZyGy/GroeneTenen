package be.vdab.web;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.vdab.entities.Filiaal;
import be.vdab.services.FiliaalService;

public class FiliaalControllerTest {

    private FiliaalController filiaalController;
    private List<Filiaal> filialen;
    private FiliaalService filiaalService;

    @Before
    public void before() {
	filialen = Collections.emptyList();
	filiaalService = mock(FiliaalService.class);
	when(filiaalService.findAll()).thenReturn(filialen);
	filiaalController = new FiliaalController(filiaalService);
    }

    @Test
    public void findAllActiveertJuisteView() {
	assertEquals("filialen/filialen", filiaalController.findAll().getViewName());
    }

    @Test
    public void findAllMaaktRequestAttribuutFilialen() {
	assertSame(filialen, filiaalController.findAll().getModelMap().get("filialen"));
    }

}
