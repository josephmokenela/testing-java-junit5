package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("Model")
public interface ModelTests {

    @BeforeEach
    default void beforeEach(TestInfo testInfo) {
        System.out.println("Running Test frm interface- " + testInfo.getDisplayName());
    }
}
