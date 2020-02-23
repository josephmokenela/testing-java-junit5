package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @Test
    public void testDependentAssertions() {
        Owner owner = new Owner(1L , "Joseph", "Mokenela");
        owner.setCity("Cape Town");
        owner.setTelephone("1234567890");

        assertAll("Properties Test",
                    ()-> assertAll("Person Properties",
                            ()-> assertEquals("Joseph", owner.getFirstName(), "First name did not match"),
                            ()-> assertEquals("Mokenela", owner.getLastName(), "Last name did not match")),
                    ()-> assertAll("Owner Properties",
                            ()-> assertEquals("Cape Town", owner.getCity(), "City did not match"),
                            ()-> assertEquals("1234567890", owner.getTelephone(), "Telephone did not match")
                ));
    }

}