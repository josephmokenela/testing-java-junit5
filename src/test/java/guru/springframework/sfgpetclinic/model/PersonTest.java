package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void groupedAssertions() {
        // given
        Person person = new Person(1L, "Joseph", "Mokenela");

        // then
        assertAll("Test props set",
                ()-> assertEquals("Joseph", person.getFirstName(), "First name failed"),
                ()-> assertEquals("Mokenela", person.getLastName(), "Last name failed"),
                ()-> assertEquals(Long.valueOf(1), person.getId(), "Id failed"));

    }
}