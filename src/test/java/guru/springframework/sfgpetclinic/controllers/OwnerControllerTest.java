package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.Invocation;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNER_CREATE_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private static final String OWNERS_PATH = "redirect:/owners/1";

    @Mock
    private OwnerService ownerService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private OwnerController ownerController;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    public void setup() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willAnswer(invocation -> {
            String name = invocation.getArgument(0);
            List<Owner> ownerList = new ArrayList<>();
            if (name.equals("%Mokenela%")) {
                ownerList.add(new Owner(1L, "Bokang", "Mokenela"));
                return ownerList;
            } else if (name.equals("%Makgatha%")) {
                return ownerList;
            } else if (name.equals("%Kelly%")) {
                ownerList.add(new Owner(1L, "Joe", "Kelly"));
                ownerList.add(new Owner(2L, "hello", "Kelly"));
                return ownerList;
            }
            throw new RuntimeException("Invalid Argument");
        });
    }

    @Test
    public void test_process_form_wildcard_string_annotation_captor() {
        // given
        Owner owner = new Owner(1L, "Bokang", "Mokenela");

        // when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%Mokenela%");
        assertThat(viewName).isEqualToIgnoringCase("redirect:/owners/1");
        verifyNoInteractions(model);

    }

    @Test
    public void test_process_form_wildcard_not_found() {
        // given
        Owner owner = new Owner(1L, "Tlotliso", "Makgatha");

        // when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%Makgatha%");
        assertThat(viewName).isEqualToIgnoringCase("owners/findOwners");
        verifyNoInteractions(model);
    }

    @Test
    public void test_process_form_wildcard_found() {
        // given
        Owner owner = new Owner(1L, "Jose", "Kelly");
        InOrder inOrder = inOrder(ownerService, model);

        // when
        String viewName = ownerController.processFindForm(owner, bindingResult, model);

        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%Kelly%");
        assertThat(viewName).isEqualToIgnoringCase("owners/ownersList");

        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model, times(1)).addAttribute(anyString(), anyList());
        verifyNoMoreInteractions(model);

    }

    @Test
    void test_process_creation_form_has_errors() {
        // given
        Owner owner = new Owner(1L, "Joseph", "Mokenela");
        given(bindingResult.hasErrors()).willReturn(true);

        // when
        String viewName = ownerController.processCreationForm(owner, bindingResult);

        // then
        assertThat(viewName).isEqualTo(OWNER_CREATE_UPDATE_FORM);
    }

    @Test
    void test_process_creation_form_has_no_errors() {
        // given
        Owner owner = new Owner(1L, "Tlotliso", "Mokenela");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);

        // when
        String viewName = ownerController.processCreationForm(owner, bindingResult);

        // then
        assertThat(viewName).isEqualTo(OWNERS_PATH);
    }

}