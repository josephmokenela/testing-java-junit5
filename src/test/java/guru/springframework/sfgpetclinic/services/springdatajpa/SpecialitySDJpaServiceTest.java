package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock(lenient = true)
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialitySDJpaService specialitySDJpaService;

    @Test
    public void test_find_by_id() {
        Speciality speciality = new Speciality();

        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality foundSpeciality = specialitySDJpaService.findById(1L);

        assertThat(foundSpeciality).isNotNull();

        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    public void test_find_by_id_bdd() {
        // given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        // when
        Speciality foundSpeciality = specialitySDJpaService.findById(1L);

        //then
        assertThat(foundSpeciality).isNotNull();
//        verify(specialtyRepository).findById(anyLong());
//        then(specialtyRepository).should().findById(anyLong()); times defaults to one
        then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();

    }

    @Test
    void test_delete_by_id() {
        //given - none

        // when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        // then
        then(specialtyRepository).should(times(2)).deleteById(anyLong());
    }

    @Test
    public void test_delete() {
        // when
        specialitySDJpaService.delete(new Speciality());

        // then
        then(specialtyRepository).should().delete(any());
    }

    @Test
    public void test_delete_by_object() {
        // given
        Speciality speciality = new Speciality();

        // when
        specialitySDJpaService.delete(speciality);

        //then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    public void test_do_throw() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, ()->  specialtyRepository.delete(new Speciality()));

        verify(specialtyRepository).delete(any());
    }

    @Test
    public void test_find_by_id_throws() {
        //given
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));

        //when
        assertThrows(RuntimeException.class, ()-> specialitySDJpaService.findById(1L));

        // then
        then(specialtyRepository).should().findById(anyLong());
    }

    @Test
    public void test_save_lambda() {
        // given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        // need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

        // when
        Speciality returnedSpecialty = specialitySDJpaService.save(speciality);

        // then
        assertThat(returnedSpecialty.getId()).isEqualTo(1L);
    }

    @Test
    public void test_save_lambda_no_match() {
        // given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription("DO_NOT_MATCH_ME");

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        // need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

        // when
        Speciality returnedSpecialty = specialitySDJpaService.save(speciality);

        // then
        assertThat(returnedSpecialty).isNull();
    }
}