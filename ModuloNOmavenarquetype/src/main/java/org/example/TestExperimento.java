package org.example;
import Procesamiento.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TestExperimento {
    private Experimento experimento;
    private Poblacion poblacion1;
    private Poblacion poblacion2;

    @BeforeEach
    void setUp() {
        experimento = new Experimento(1, "Experimento 1", null);
        poblacion1 = new Poblacion("Poblacion1", 1, new Date(), new Date(), 100, 37.0f, Luminosidad.ALTA, new Dosis(150,15,250,150));
        poblacion2 = new Poblacion("Poblacion2", 2, new Date(), new Date(), 200, 37.0f, Luminosidad.BAJA, new Dosis(100,15,200,100));
    }

    @Test
    void shouldAddPoblacion() {
        experimento.agregarPoblacion(poblacion1);
        assertTrue(experimento.getPoblaciones().contains(poblacion1));
    }

    @Test
    void shouldNotAddNullPoblacion() {
        experimento.agregarPoblacion(null);
        assertFalse(experimento.getPoblaciones().contains(null));
    }

    @Test
    void shouldRemovePoblacion() {
        experimento.agregarPoblacion(poblacion1);
        experimento.borrarPoblacion(poblacion1);
        assertFalse(experimento.getPoblaciones().contains(poblacion1));
    }

    @Test
    void shouldNotRemoveNonExistentPoblacion() {
        experimento.agregarPoblacion(poblacion1);
        experimento.borrarPoblacion(poblacion2);
        assertTrue(experimento.getPoblaciones().contains(poblacion1));
    }

    @Test
    void shouldCreatePoblacion() {
        experimento.crearPoblacion("Poblacion3", 3, new Date(), new Date(), 300, 37.0f, Luminosidad.MEDIA, new Dosis(160,15,200,100));
        assertEquals(1, experimento.getPoblaciones().size());
        assertEquals("Poblacion3", experimento.getPoblaciones().get(0).getNombre());
    }
}