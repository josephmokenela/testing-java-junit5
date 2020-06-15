package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class PersonRepeatedTests implements ModelRepeatedtests {

    @RepeatedTest(value = 10, name = "{displayName} - {currentRepetition} - {totalRepetitions}")
    @DisplayName("My Repeated test")
    public void repeated_test() {
        System.out.println("This has to run 10 times");
    }

    @RepeatedTest(value = 5)
    void repeated_test_with_di(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(value = 5, name = "{displayName} - {currentRepetition} | {totalRepetitions}")
    @DisplayName("My Assignment repeated test")
    void my_assignment_repeated() {
        System.out.println("My assignment repeated test");
    }
}
