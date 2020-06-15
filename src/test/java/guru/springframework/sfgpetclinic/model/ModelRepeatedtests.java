package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("ModelRepeated")
public interface ModelRepeatedtests {

    @BeforeEach
    default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Running Test frm interface- " + testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }
}
