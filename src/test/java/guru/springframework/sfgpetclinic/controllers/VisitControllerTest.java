package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    private VisitService visitService;

//    @Mock
//    private PetService petService;

    @Spy
    private PetService petService;

    @InjectMocks
    private VisitController visitController;

    @Test
    public void test_pets_with_visits() {
        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);
        given(petService.findById(anyLong())).willReturn(pet);

        // when
        Visit visit = visitController.loadPetWithVisit(1L, model);

        // then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
    }

    @Test
    public void test_pets_with_visits_using_spy() {
        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);
        petService.save(pet);
        given(petService.findById(anyLong())).willCallRealMethod();

        // when
        Visit visit = visitController.loadPetWithVisit(1L, model);

        // then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(1L);
    }
}