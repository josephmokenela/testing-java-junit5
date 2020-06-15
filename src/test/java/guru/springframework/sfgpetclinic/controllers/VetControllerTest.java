package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelMapImpl;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VetControllerTest implements ControllerTests {

    private VetService vetService;
    private SpecialtyService specialtyService;
    private VetController vetController;

    @BeforeEach
    public void setup() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);
        vetController = new VetController(vetService);

        Vet vet1 = new Vet(1L, "Joseph", "Mokenela", null);
        Vet vet2 = new Vet(2L, "Tlotliso", "Makhatha", null);

        vetService.save(vet1);
        vetService.save(vet2);

    }

    @Test
    void listVets() {
        Model model = new ModelMapImpl();
        String view = vetController.listVets(model);

        assertThat(view).isEqualTo("vets/index");

        Set modelAttribute = (Set) model.getModelMap().get("vets");

        assertThat(modelAttribute.size()).isEqualTo(2);
    }
}