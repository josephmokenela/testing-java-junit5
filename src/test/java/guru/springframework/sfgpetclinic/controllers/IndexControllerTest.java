package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    IndexController indexController;

    @BeforeEach
    public void setup() {
        indexController = new IndexController();
    }

    @Test
    public void index() {
        assertEquals("index", indexController.index(), ()-> "Evaluated on failure, Expensive call");
    }

    @Test
    public void oupsHandler() {
        assertEquals("notimplemented", indexController.oupsHandler(), ()-> "Evaluated on failure, Expensive call");
    }
}