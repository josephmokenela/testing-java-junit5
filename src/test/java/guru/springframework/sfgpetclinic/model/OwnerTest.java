package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.controllers.CustomArgumentsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTests {

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

    @DisplayName("Value source test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"Java", "Spring", "Framework", "Guru"})
    public void test_value_source(String value) {
        System.out.println(value);
    }

    @DisplayName("Enum source test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    public void test_enum_source(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("CSV Input test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource({"GP, 1, 1", "CT, 2, 2", "KZN, 3, 3"})
    public void test_csv_input(String stateName, int firstValue, int secondValue) {
        System.out.println(stateName + " " + firstValue + " " + secondValue);
    }

    @DisplayName("CSV from file test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    public void test_csv_file_input(String stateName, int firstValue, int secondValue) {
        System.out.println(stateName + " " + firstValue + " " + secondValue);
    }

    @DisplayName("Method Provider test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArguments")
    public void test_from_method(String stateName, int firstValue, int secondValue) {
        System.out.println(stateName + " " + firstValue + " " + secondValue);
    }

    static Stream<Arguments> getArguments() {
        return Stream.of(Arguments.of("CT", 1, 1), Arguments.of("MSU", 2, 2), Arguments.of("BFN", 3, 3));
    }

    @DisplayName("Custom Provider test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ArgumentsSource(CustomArgumentsProvider.class)
    public void test_from_class(String stateName, int firstValue, int secondValue) {
        System.out.println(stateName + " " + firstValue + " " + secondValue);
    }

}